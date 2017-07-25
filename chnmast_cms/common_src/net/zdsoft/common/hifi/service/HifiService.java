/*
 * @(#)HifiService.java    Created on 2016年9月22日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.service;

import java.util.List;
import java.util.Map;

import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.hifi.dto.HifiAlbum;
import net.zdsoft.common.hifi.dto.HifiArtist;
import net.zdsoft.common.hifi.dto.HifiArtistType;
import net.zdsoft.common.hifi.dto.HifiHomeColumn;
import net.zdsoft.common.hifi.dto.HifiHomeColumnContent;
import net.zdsoft.common.hifi.dto.HifiMember;
import net.zdsoft.common.hifi.dto.HifiMusic;

/**
 * Hifi对接业务数据处理
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年9月22日 下午3:20:55 $
 */
public interface HifiService {

    /**
     * 根据平台用户信息注册hifi账户（accountNo是后面使用的Apikey）
     *
     * @param new_uesrId
     *            传递给hifi的uuid（对应userid）
     * @param avatarUrl
     * @param userRealName
     * @return key：账户，头像，名字<br>
     *         hifi_accountNo，hifi_avatarUrl，hifi_name
     */
    public Map<String, String> getHiFiAccountByUser(String new_uesrId, String avatarUrl, String userRealName);

    /**
     * 获取每周推荐数据列表
     *
     * @param apikey
     * @return
     */
    public List<HifiHomeColumnContent> getRecommendColumnContentList(String apikey);

    /**
     * 获取首页栏目及栏目内容列表
     *
     * @param apikey
     * @return
     */
    public List<HifiHomeColumn> getHomeColumnList(String apikey);

    /**
     * 获取专辑类型列表（缓存）
     *
     * @param apikey
     * @return
     */
    public List<HifiHomeColumn> getColumnMenuTypeList(String apikey);

    /**
     * 根据专辑id获取专辑详情
     *
     * @param apikey
     * @param id
     *            albumId
     * @return
     */
    public HifiAlbum getHifiAlbumById(String apikey, String id);

    /**
     * 根据歌曲id获取歌曲详情
     *
     * @param apikey
     * @param id
     *            trackId
     * @return
     */
    public HifiMusic getHifiMusicById(String apikey, String id);

    /**
     * 根据演出者id获取演出者详情
     *
     * @param apikey
     * @param id
     *            artistId
     * @return
     */
    public HifiArtist getArtistInfoById(String apikey, String id);

    /**
     * 获取演出者分类列表
     *
     * @param apikey
     * @return
     */
    public List<HifiArtistType> getArtistTypes(String apikey);

    /**
     * 根据演唱者分类获取演唱者列表<br>
     * 注：只演唱者基础信息（id,name,imgUrl），如需要完整的演唱者信息，需调用获取演唱者详情业务
     *
     * @param apikey
     * @param typeId
     *            演出者分类id
     * @param page
     *            分页对象必传，传null返回空列表
     * @return
     */
    public List<HifiArtist> getHifiArtistListByType(String apikey, int typeId, PageDto page);

    /**
     * 根据演出者id获取专辑列表<br>
     * 注：专辑基础信息，如需要完整的专辑信息，需调用获取专辑详情业务
     *
     * @param apikey
     * @param artistId
     *            演出者id
     * @param page
     *            分页对象必传，传null返回空列表
     * @return
     */
    public List<HifiAlbum> getHifiAlbumListByArtistId(String apikey, String artistId, PageDto page);

    /**
     * 根据首页栏目id获取栏目"更多"接口（专辑列表） <br>
     * 注：专辑基础信息，如需要完整的专辑信息，需调用获取专辑详情业务
     *
     * @param apikey
     * @param menuId
     *            栏目id
     * @param page
     *            分页对象必传，传null返回空列表
     * @return
     */
    public List<HifiAlbum> getHifiAlbumListByMenuId(String apikey, int menuId, PageDto page);

    /**
     * 根据歌曲listenUrl获取歌曲实际播放地址
     *
     * @param apikey
     * @param listenUrl
     *            歌曲试听地址
     * @return
     */
    public String getMusicListenUrl(String apikey, String listenUrl);

    /**
     * 根据歌曲playUrl获取16-bit/flac播放流媒体值
     *
     * @param apikey
     * @param playUrl
     * @return "url"：flac格式的地址<br>
     *         "date"<br>
     *         "token"：要摆在请求flac地址时的http头<br>
     *         "authorization"<br>
     */
    public Map<String, Object> getFlacPlayUrl(String apikey, String playUrl);

    /**
     * 获取个人会员信息
     *
     * @param apikey
     * @return
     */
    public HifiMember getHifiMemberByAccountNo(String apikey);

}
