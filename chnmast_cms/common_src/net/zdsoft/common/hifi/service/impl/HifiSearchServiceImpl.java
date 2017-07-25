/*
 * @(#)HifiSearchServiceImpl.java    Created on 2016年10月19日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.hifi.dto.HifiAlbum;
import net.zdsoft.common.hifi.dto.HifiArtist;
import net.zdsoft.common.hifi.dto.HifiMusic;
import net.zdsoft.common.hifi.service.HifiSearchService;
import net.zdsoft.common.hifi.util.HifiApiUtil;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年10月19日 上午11:28:50 $
 * @param <T>
 */
@SuppressWarnings("rawtypes")
@Service("hifiServiceService")
public class HifiSearchServiceImpl<T> extends HifiServiceImpl implements HifiSearchService {

    @SuppressWarnings("unchecked")
    @Override
    public T search(String apikey, int searchType, String key, PageDto page) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Map<String, List<?>> map = new HashMap<>();
        if (page == null) {
            return null;
        }
        if (page.getCurrentPage() == 0) {
            page.setCurrentPage(1);// 从第一页开始
        }
        int maxitem = page.getRowNum();// 每页显示记录数
        if (searchType == 0) {
            // 如查询全部，则计算每个分类查询记录数
            maxitem = page.getRowNum() / 3;
        }
        int startitem = (page.getCurrentPage() - 1) * maxitem;// 分页开始的偏移量
        try {
            JSONObject obj = HifiApiUtil.search(apikey, searchType, maxitem, startitem, key);
            if (isGetData(obj)) {
                JSONArray array = (JSONArray) JSON.parse(obj.getString("list"));// 对接获取的列表数据
                page.setSelectRows(array.size());// 查询到的记录数
                int total = obj.getIntValue("total");
                page.setTotalNum(total);
                if (searchType == 0) {// 查询全部
                    int artistNum = obj.getIntValue("artist");
                    int albumNum = obj.getIntValue("album");
                    int musicNum = obj.getIntValue("music");
                    // 排序，取最大数组，做为分页总数
                    int maxNum = artistNum;
                    if (maxNum < albumNum) {
                        maxNum = albumNum;
                    }
                    if (maxNum < musicNum) {
                        maxNum = musicNum;
                    }
                    page.setTotalNum(maxNum);// 查询全部，则取不同分类中最大的记录数做为分页总数
                }
                map = getSearchResult(array);
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-调用HiFi搜索功能】" + e.getMessage());
            return null;
        }
        if (searchType == 10) {// 演出者列表
            return (T) map.get("artist");
        }
        else if (searchType == 1) {// 专辑列表
            return (T) map.get("album");
        }
        else if (searchType == 5) {// 歌曲列表
            return (T) map.get("music");
        }
        else if (searchType == 0) {// 全部
            return (T) map;
        }
        else {
            return null;
        }
    }

    /**
     * 搜索到的数据，分类型转换
     *
     * @param array
     * @return key：艺术家列表，专辑列表，歌曲列表（artist，album，music）
     */
    private Map<String, List<?>> getSearchResult(JSONArray array) {
        Map<String, List<?>> map = new HashMap<>();
        HifiArtist artist = null;
        HifiAlbum album = null;
        HifiMusic music = null;
        JSONObject json = null;

        List<HifiArtist> artistList = new ArrayList<>();
        List<HifiAlbum> albumList = new ArrayList<>();
        List<HifiMusic> musicList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            json = array.getJSONObject(i);
            int type = json.getIntValue("type");
            switch (type) {
            case 10:// 艺术家
                artist = new HifiArtist();
                artist.setId(json.getString("contentid"));
                artist.setName(json.getString("name"));
                artist.setImgUrl(json.getString("imgurl"));
                artistList.add(artist);
                break;
            case 1:// 专辑
                album = new HifiAlbum();
                album.setId(json.getString("contentid"));
                album.setName(json.getString("name"));
                album.setSmallimg(json.getString("imgurl"));
                album.setArtistId(json.getString("artistid"));
                album.setArtistName(json.getString("artistname"));
                albumList.add(album);
                break;
            case 5:// 单曲
                music = new HifiMusic();
                music.setId(json.getString("contentid"));
                music.setName(json.getString("name"));
                music.setArtistId(json.getString("artistid"));
                music.setArtistName(json.getString("artistname"));
                music.setAlbumId(json.getString("albumid"));
                music.setAlbumName(json.getString("albumname"));
                music.setAlbumImg(json.getString("albumimg"));
                music.setPlayTimes(json.getString("recordingtime"));
                musicList.add(music);
                break;
            default:
                break;
            }
        }
        if (CollectionUtils.isNotEmpty(artistList)) {
            map.put("artist", artistList);
        }
        if (CollectionUtils.isNotEmpty(albumList)) {
            map.put("album", albumList);
        }
        if (CollectionUtils.isNotEmpty(musicList)) {
            map.put("music", musicList);
        }
        return map;
    }

    @Override
    public List<String> get5HotspotsBySearchKey(String apikey, String key) {
        List<String> list = new ArrayList<>();
        try {
            JSONObject obj = HifiApiUtil.get5HotspotsBySearchKey(apikey, key);
            if (isGetData(obj)) {
                JSONArray array = (JSONArray) JSON.parse(obj.getString("list"));// 对接获取的列表数据
                JSONObject json = null;
                for (int i = 0; i < array.size(); i++) {
                    json = array.getJSONObject(i);
                    list.add(json.getString("chineseChar"));
                }
            }
        }
        catch (Exception e) {
            log.error("【调用HiFi接口失败-service-调用HiFi搜索热点词功能】" + e.getMessage());
            return null;
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        HifiSearchServiceImpl<?> service = new HifiSearchServiceImpl();
        String apikey = "1474265419748";
        PageDto page = new PageDto();
        List<HifiMusic> a = (List<HifiMusic>) service.search(apikey, 5, "阿", page);
        System.out.println(page.getTotalPage());
        System.out.println(a);

        System.exit(0);
    }

}
