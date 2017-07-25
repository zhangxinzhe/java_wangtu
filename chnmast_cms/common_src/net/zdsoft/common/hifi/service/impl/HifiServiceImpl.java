/*
 * @(#)HifiServiceImpl.java    Created on 2016年9月23日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.cache.BaseCacheServiceImpl;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.hifi.dto.HifiAlbum;
import net.zdsoft.common.hifi.dto.HifiArtist;
import net.zdsoft.common.hifi.dto.HifiArtistType;
import net.zdsoft.common.hifi.dto.HifiHomeColumn;
import net.zdsoft.common.hifi.dto.HifiHomeColumnContent;
import net.zdsoft.common.hifi.dto.HifiMember;
import net.zdsoft.common.hifi.dto.HifiMusic;
import net.zdsoft.common.hifi.service.HifiService;
import net.zdsoft.common.hifi.util.HifiApiUtil;
import net.zdsoft.keel.util.DateUtils;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年9月23日 上午10:09:12 $
 */
@Service("hifiService")
public class HifiServiceImpl extends BaseCacheServiceImpl implements HifiService {
    protected static final Logger log = LoggerFactory.getLogger(HifiServiceImpl.class);

    private static final String EXCLUDE_MENU_NAME = "Hi-Res,5.1环绕声,音乐商城";// 首页栏目，排除的模块名称
    private static final String RECOMMEND_MENU_NAME = "精选";// 首页栏目-推荐栏目名称

    /**
     * hifi对接，是否获取到数据
     *
     * @return 获取到数据ture/为获取到数据false
     */
    public boolean isGetData(JSONObject object) {
        if (object == null) {
            return false;
        }
        if (StringUtils.isNotBlank(object.getString("error_code"))) {
            log.error(object.getString("error_value"));// 打印信息
            return false;
        }
        return true;
    }

    @Override
    public Map<String, String> getHiFiAccountByUser(String new_uesrId, String avatarUrl, String userRealName) {
        Map<String, String> hifiAccountMap = new HashMap<>();
        try {
            JSONObject obj = HifiApiUtil.getHiFiAccountByUser(new_uesrId, avatarUrl, userRealName);
            if (isGetData(obj)) {
                String hifi_accountNo = obj.getString("accountNo");
                String hifi_avatarUrl = obj.getString("avatarUrl");
                String hifi_name = obj.getString("userName");
                hifiAccountMap.put("hifi_accountNo", hifi_accountNo);
                hifiAccountMap.put("hifi_avatarUrl", hifi_avatarUrl);
                hifiAccountMap.put("hifi_name", hifi_name);
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-注册HiFi账户】" + e.getMessage());
            return null;
        }
        return hifiAccountMap;
    }

    @Override
    public List<HifiHomeColumnContent> getRecommendColumnContentList(String apikey) {
        List<HifiHomeColumn> ColumnList = getHomeColumnByType(apikey, 1);
        List<HifiHomeColumnContent> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(ColumnList)) {
            HifiHomeColumn column = ColumnList.get(0);
            list = column.getContentList();
        }
        return list;
    }

    @Override
    public List<HifiHomeColumn> getHomeColumnList(String apikey) {
        return getHomeColumnByType(apikey, 2);
    }

    @Override
    public List<HifiHomeColumn> getColumnMenuTypeList(String apikey) {
        @SuppressWarnings("unchecked")
        List<HifiHomeColumn> list = (List<HifiHomeColumn>) getCache("CHNMASTER_HIFI_ALBUM_TYPES");
        if (CollectionUtils.isEmpty(list)) {
            list = getHomeColumnByType(apikey, 3);
            putCache("CHNMASTER_HIFI_ALBUM_TYPES", list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 获取首页栏目和每周推荐
     *
     * @param apikey
     * @param type
     *            1每周推荐；2首页栏目；3只获取专辑类型列表
     * @return
     */
    private List<HifiHomeColumn> getHomeColumnByType(String apikey, int type) {
        List<HifiHomeColumn> list = new ArrayList<>();
        try {
            JSONObject obj = HifiApiUtil.getHomeColumns(apikey);
            if (isGetData(obj)) {
                // 栏目个数
                int count = obj.getIntValue("COUNT");
                if (count > 0) {
                    HifiHomeColumn column = null;
                    List<HifiHomeColumnContent> contentList = null;
                    HifiHomeColumnContent columnContent = null;
                    JSONObject colmnJson = null;
                    String menus = obj.getString("menus");
                    JSONArray array = (JSONArray) JSON.parse(menus);
                    for (int i = 0; i < array.size(); i++) {
                        column = new HifiHomeColumn();
                        colmnJson = array.getJSONObject(i);
                        String menuType = colmnJson.getString("menutype");
                        // 只保留专题类型的数据
                        if (!"album".equals(menuType)) {
                            continue;
                        }
                        if (colmnJson != null) {
                            String menuname = colmnJson.getString("menuname");
                            // 排除栏目
                            if (EXCLUDE_MENU_NAME.contains(menuname)) {
                                continue;
                            }

                            if (type == 1) {// 每周推荐
                                if (!RECOMMEND_MENU_NAME.equals(menuname)) {
                                    continue;
                                }
                            }
                            else if (type == 2 || type == 3) {// 首页栏目
                                if (RECOMMEND_MENU_NAME.equals(menuname)) {
                                    continue;
                                }
                            }

                            // set栏目基础信息
                            column.setMenuId(colmnJson.getIntValue("menuid"));
                            column.setMenuName(menuname);
                            column.setDisplayOrder(colmnJson.getIntValue("displayorder"));
                            column.setMenuType(menuType);

                            if (type == 3) {// 只获取专辑类型列表，无需内容列表
                                list.add(column);
                                continue;
                            }

                            // set栏目content列表
                            contentList = new ArrayList<>();
                            JSONArray columnArray = (JSONArray) JSON.parse(colmnJson.getString("sliderContent"));
                            for (int k = 0; k < columnArray.size(); k++) {
                                columnContent = new HifiHomeColumnContent();
                                JSONObject contentJson = columnArray.getJSONObject(k);
                                if (contentJson != null) {
                                    String contentType = contentJson.getString("type");
                                    // 只保留专题类型的数据
                                    if (!"album".equals(contentType)) {
                                        continue;
                                    }
                                    columnContent.setType(contentType);
                                    columnContent.setContentId(contentJson.getString("contentId"));
                                    columnContent.setContentTitle(contentJson.getString("contentTitle"));
                                    columnContent.setImgUrl(contentJson.getString("imgUrl"));
                                    columnContent.setPrice(contentJson.getFloatValue("price"));
                                    columnContent.setDisplayOrder(contentJson.getIntValue("displayOrder"));
                                    columnContent.setAlbumName(contentJson.getString("albumName"));
                                    columnContent.setArtistName(contentJson.getString("artistName"));
                                    columnContent.setLinkUrl(contentJson.getString("linkUrl"));
                                }
                                contentList.add(columnContent);
                            }
                            column.setContentList(contentList);
                        }
                        list.add(column);
                    }
                }
            }
        }
        catch (Exception e) {
            String tip = "";
            if (type == 1) {
                tip = "每周推荐";
            }
            else if (type == 2) {
                tip = "首页栏目";
            }
            else if (type == 3) {
                tip = "专辑类型";
            }
            log.error("【调用HiFi接口失败-service-获取HiFi首页栏目信息-" + tip + "】" + e.getMessage());
            return null;
        }
        // 栏目排序
        if (CollectionUtils.isNotEmpty(list)) {
            Collections.sort(list, new Comparator<HifiHomeColumn>() {
                @Override
                public int compare(HifiHomeColumn o1, HifiHomeColumn o2) {
                    return o1.getDisplayOrder() >= o2.getDisplayOrder() ? 1 : -1;
                }
            });
        }
        return list;
    }

    @Override
    public HifiAlbum getHifiAlbumById(String apikey, String id) {
        HifiAlbum album = new HifiAlbum();
        try {
            JSONObject obj = HifiApiUtil.getAlbumInfo(apikey, id);
            if (isGetData(obj)) {
                // 专辑基础信息
                album.setId(obj.getString("id"));
                album.setName(obj.getString("name"));
                album.setCn_name(obj.getString("cn_name"));
                album.setBigimg(obj.getString("bigimg"));
                album.setSmallimg(obj.getString("smallimg"));
                album.setIntroduction(obj.getString("introduction"));
                album.setPublishTime(obj.getDate("publishtime"));
                album.setLanguage(obj.getString("language"));
                album.setTechnology(obj.getString("technology"));
                album.setArtistName(obj.getString("artists"));
                album.setCompanyName(obj.getString("companyname"));
                album.setSize(obj.getFloatValue("size"));
                album.setMusicCount(obj.getIntValue("musiccount"));
                album.setPlayTime(obj.getString("playtime"));
                album.setPrice(obj.getFloatValue("price"));
                album.setReference(obj.getString("reference"));
                album.setProductId(obj.getString("productid"));
                album.setAafid(obj.getString("aafid"));
                album.setIsfullflac(obj.getBooleanValue("isfullflac"));

                // 专辑中歌曲列表
                List<HifiMusic> musicList = new ArrayList<>();
                JSONArray tempArray = (JSONArray) JSON.parse(obj.getString("disks"));
                if (tempArray.size() > 0) {
                    HifiMusic music = null;
                    JSONObject musicJson = null;
                    JSONArray array = (JSONArray) JSON.parse(tempArray.getJSONObject(0).getString("musics"));
                    for (int i = 0; i < array.size(); i++) {
                        music = new HifiMusic();
                        musicJson = array.getJSONObject(i);
                        music.setId(musicJson.getString("id"));
                        music.setName(musicJson.getString("name"));
                        music.setArtistId(musicJson.getString("artistid"));
                        music.setArtistName(musicJson.getString("artist"));
                        // music.setSmallimg(musicJson.getString(""));
                        music.setPlayTimes(musicJson.getString("totaltime"));// 单曲时长
                        // music.setTechnology(musicJson.getString(""));
                        // music.setDownloadUrl(musicJson.getString(""));
                        music.setListenUrl(musicJson.getString("testurl"));// map3试听地址
                        music.setPlayUrl(musicJson.getString("playurl"));// 高音质 16-bit flac试听/下载地址（开通会员才能试听16bit flac格式）
                        // music.setLyrics(musicJson.getString(""));
                        music.setPrice(musicJson.getFloatValue("price"));
                        // music.setSize(musicJson.getIntValue(""));
                        // music.setMp3Avail(musicJson.getBooleanValue(""));
                        // music.setProductid(musicJson.getString(""));
                        music.setTafid(musicJson.getString("tafid"));
                        music.setAlbumId(musicJson.getString("albumid"));
                        music.setAlbumName(musicJson.getString("albumname"));
                        music.setAlbumImg(musicJson.getString("albumimg"));
                        // music.setPublishTime(musicJson.getDate(""));
                        // music.setLanguage(musicJson.getString(""));
                        // music.setCompanyName(musicJson.getString(""));
                        music.setTrackno(musicJson.getIntValue("trackno"));// 歌曲在专辑中排序
                        musicList.add(music);
                    }
                }
                album.setMusicList(musicList);
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-获取HiFi专辑详情】" + e.getMessage());
            return null;
        }
        return album;
    }

    @Override
    public HifiMusic getHifiMusicById(String apikey, String id) {
        HifiMusic music = new HifiMusic();
        try {
            JSONObject obj = HifiApiUtil.getMusicInfo(apikey, id);
            if (isGetData(obj)) {
                music.setId(obj.getString("id"));
                music.setName(obj.getString("name"));
                music.setArtistId(obj.getString("artistid"));
                music.setArtistName(obj.getString("artistname"));
                music.setSmallimg(obj.getString("smallimg"));
                music.setPlayTimes(obj.getString("playtimes"));// 单曲时长
                music.setTechnology(obj.getString("technology"));
                music.setDownloadUrl(obj.getString("downloadUrl"));
                music.setListenUrl(obj.getString("listenurl"));// map3试听地址
                music.setPlayUrl(obj.getString("playurl"));// 高音质 16-bit flac试听/下载地址（开通会员才能试听16bit flac格式）
                music.setLyrics(obj.getString("lyrics"));
                music.setPrice(obj.getFloatValue("price"));
                music.setSize(obj.getIntValue("size"));
                music.setMp3Avail(obj.getBooleanValue("mp3Avail"));
                music.setProductid(obj.getString("productid"));
                music.setTafid(obj.getString("tafid"));
                music.setAlbumId(obj.getString("albumid"));
                music.setAlbumName(obj.getString("albumname"));
                music.setAlbumImg(obj.getString("albumimg"));
                music.setPublishTime(obj.getDate("publishtime"));
                music.setLanguage(obj.getString("language"));
                music.setCompanyName(obj.getString("companyname"));
                music.setTrackno(obj.getIntValue("trackno"));// 歌曲在专辑中排序
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-获取HiFi歌曲详情】" + e.getMessage());
            return null;
        }
        return music;
    }

    @Override
    public HifiArtist getArtistInfoById(String apikey, String id) {
        HifiArtist artist = new HifiArtist();
        try {
            JSONObject obj = HifiApiUtil.getArtistInfo(apikey, id);
            if (isGetData(obj)) {
                artist.setId(obj.getString("artistId"));
                artist.setName(obj.getString("artistName"));
                artist.setAliasName(obj.getString("aliasName"));
                artist.setForeignName(obj.getString("foreignName"));
                artist.setFirstChar(obj.getString("firstChar"));
                artist.setGender(obj.getString("gender"));
                artist.setBeginDate(obj.getDate("beginDate"));
                artist.setEndDate(obj.getDate("endDate"));
                artist.setImgUrl(obj.getString("imgurl"));
                artist.setIntroduction(obj.getString("introduction"));
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-获取HiFi演唱者详情】" + e.getMessage());
            return null;
        }
        return artist;
    }

    @Override
    public List<HifiArtistType> getArtistTypes(String apikey) {
        List<HifiArtistType> list = new ArrayList<HifiArtistType>();
        try {
            JSONArray array = HifiApiUtil.getArtistTypes(apikey);
            if (array != null && array.size() > 0) {
                HifiArtistType artist = null;
                JSONObject artistJson = null;
                for (int i = 0; i < array.size(); i++) {
                    artist = new HifiArtistType();
                    artistJson = array.getJSONObject(i);
                    artist.setId(artistJson.getString("id"));
                    artist.setName(artistJson.getString("name"));
                    artist.setImage(artistJson.getString("image"));
                    list.add(artist);
                }
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-获取HiFi演出者分类列表】" + e.getMessage());
            return null;
        }
        return list;
    }

    @Override
    public List<HifiArtist> getHifiArtistListByType(String apikey, int typeId, PageDto page) {
        List<HifiArtist> list = new ArrayList<HifiArtist>();
        if (page == null) {
            return list;
        }
        if (page.getCurrentPage() == 0) {
            page.setCurrentPage(1);// 从第一页开始
        }
        int maxitem = page.getRowNum();// 每页显示记录数
        int startitem = (page.getCurrentPage() - 1) * maxitem;// 分页开始的偏移量
        try {
            JSONObject obj = HifiApiUtil.getArtistListByType(apikey, typeId, maxitem, startitem);
            if (isGetData(obj)) {
                JSONArray array = (JSONArray) JSON.parse(obj.getString("list"));// 对接获取的列表数据
                int totalNum = obj.getIntValue("total");// 对接获取的总记录数
                int selectRows = array.size();
                page.setTotalNum(totalNum);// 总页数
                page.setSelectRows(selectRows);// 本次查询到的记录数

                // 数据处理
                HifiArtist artist = null;
                JSONObject artistJson = null;
                for (int i = 0; i < array.size(); i++) {
                    artist = new HifiArtist();
                    artistJson = array.getJSONObject(i);
                    artist.setId(artistJson.getString("id"));
                    artist.setName(artistJson.getString("name"));
                    artist.setImgUrl(artistJson.getString("smallimg"));
                    list.add(artist);
                }
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-获取HiFi演唱者列表】" + e.getMessage());
            return null;
        }
        return list;
    }

    @Override
    public List<HifiAlbum> getHifiAlbumListByArtistId(String apikey, String artistId, PageDto page) {
        // 根据艺人artistId查专辑列表时请传type=3
        return getHifiAlbumList(3, apikey, artistId, page);
    }

    @Override
    public List<HifiAlbum> getHifiAlbumListByMenuId(String apikey, int menuId, PageDto page) {
        // 根据首页栏目menuid查专辑列表时请传type=2
        return getHifiAlbumList(2, apikey, menuId + "", page);
    }

    /**
     * 根据类型id获取专辑列表
     *
     * @param type
     *            根据menuId查type=2、根据artistId查type=3
     * @param apikey
     * @param id
     * @param page
     */
    private List<HifiAlbum> getHifiAlbumList(int type, String apikey, String id, PageDto page) {
        List<HifiAlbum> list = new ArrayList<>();
        if (page == null) {
            return list;
        }
        if (page.getCurrentPage() == 0) {
            page.setCurrentPage(1);// 从第一页开始
        }
        int maxitem = page.getRowNum();// 每页显示记录数
        int startitem = (page.getCurrentPage() - 1) * maxitem;// 分页开始的偏移量

        try {
            JSONObject obj = null;
            if (type == 2) {
                obj = HifiApiUtil.getAlbumListByMenuId(apikey, id, maxitem, startitem);
            }
            else if (type == 3) {
                obj = HifiApiUtil.getAlbumListByArtistId(apikey, id, maxitem, startitem);
            }
            if (isGetData(obj)) {
                JSONArray array = (JSONArray) JSON.parse(obj.getString("album"));// 列表数据
                int totalNum = obj.getIntValue("total");// 总记录数
                int num = obj.getIntValue("num");// 本次查询到的记录数
                page.setTotalNum(totalNum);
                page.setSelectRows(num);

                // 数据处理
                HifiAlbum album = null;
                JSONObject albumJson = null;
                for (int i = 0; i < array.size(); i++) {
                    album = new HifiAlbum();
                    albumJson = array.getJSONObject(i);
                    album.setId(albumJson.getString("id"));
                    album.setName(albumJson.getString("name"));
                    album.setCn_name(albumJson.getString("cn_name"));
                    album.setArtistName(albumJson.getString("artist"));
                    album.setSmallimg(albumJson.getString("smallimg"));
                    list.add(album);
                }
            }
        }
        catch (Exception e) {
            String tip = "";
            if (type == 2) {
                tip = "【调用HiFi接口失败-service-获取HiFi首页栏目专辑列表】";
            }
            else if (type == 3) {
                tip = "【调用HiFi接口失败-service-获取HiFi演唱者专辑列表】";
            }
            log.error(tip + e.getMessage());
            return null;
        }
        return list;
    }

    @Override
    public String getMusicListenUrl(String apikey, String listenUrl) {
        String url = "";
        try {
            url = HifiApiUtil.getMusicListenUrl(apikey, listenUrl);
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-获取HiFi歌曲mp3实际播放地址】" + e.getMessage());
            return null;
        }
        return url;
    }

    @Override
    public Map<String, Object> getFlacPlayUrl(String apikey, String playUrl) {
        Map<String, Object> map = new HashMap<>();
        try {
            JSONObject obj = HifiApiUtil.getFlacPlayUrl(apikey, playUrl);
            if (isGetData(obj)) {
                String url = obj.getString("url");
                String date = obj.getString("date");
                String token = obj.getString("x-oss-security-token");
                String authorization = obj.getString("Authorization");
                map.put("url", url);
                map.put("date", date);
                map.put("token", token);
                map.put("authorization", authorization);
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-获取HiFi高保真音乐播放地址】" + e.getMessage());
            return null;
        }
        return map;
    }

    @Override
    public HifiMember getHifiMemberByAccountNo(String apikey) {
        HifiMember member = new HifiMember();
        try {
            JSONObject obj = HifiApiUtil.getMemberInfoAndNewMonthlyProduct(apikey);
            if (isGetData(obj)) {
                // 是否开通会员
                String str = obj.getString("inService");
                if (StringUtils.isNotBlank(str) && "Y".equals(str)) {
                    member.setInService(true);
                }
                else {
                    member.setInService(false);
                }
                // 会员开始时间
                Date date = obj.getDate("startDate");
                if (date != null) {
                    member.setStartDate(DateUtils.getStartDate(date));
                }
                else {
                    member.setStartDate(null);
                }
                // 会员结束时间
                date = obj.getDate("endDate");
                if (date != null) {
                    member.setEndDate(date);
                }
                else {
                    member.setEndDate(null);
                }

                member.setCurdate(obj.getDate("curdate"));
                member.setTotal(obj.getIntValue("total"));
                member.setLeftCount(obj.getIntValue("leftCount"));
                member.setAdditonnal(obj.getString("additional"));
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-获取VIP套餐详情】" + e.getMessage());
            return null;
        }
        return member;
    }

    public static void main(String[] args) {
        HifiServiceImpl service = new HifiServiceImpl();
        // HifiMember member = service.getHifiMemberByAccountNo("1476948030434");
        // System.out.println(member.getStartDate());
        // System.out.println(member.getEndDate());
        service.getFlacPlayUrl("1476948229816", "http://mg2.zhenxian.fm/mediatest2/access/stream/audio/1452670978004");
    }

}
