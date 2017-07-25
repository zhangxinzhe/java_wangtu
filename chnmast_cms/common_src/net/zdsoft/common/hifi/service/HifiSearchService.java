/*
 * @(#)HifiSeachService.java    Created on 2016年10月19日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.hifi.service;

import java.util.List;

import net.zdsoft.common.entity.PageDto;

/**
 * Hifi对接业务数据-搜索业务
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年10月19日 上午11:27:37 $
 * @param <T>
 */

public interface HifiSearchService<T> {

    /**
     * 根据搜索类型和关键字搜索
     *
     * @param apikey
     * @param searchType
     *            搜索类型（1搜索专辑，5搜索单曲，10搜索艺术家，0搜索全部）<br>
     *            注： 传0时，搜索专辑、单曲、艺术家三个分类 <br>
     *            计算每个分类应查询记录数：总记录数（每页显示条数）/3，求整舍余；如共查20条数据，则每个分类分配值20/3=6<br>
     *            当查询需有偏移量时（分页效果），则三个分类同时偏移，（分页效果对三个分类同时起效）<br>
     *
     * @param key
     *            关键字
     *
     * @param page
     *
     * @return searchType==10，返回List<'HifiArtist><br>
     *         searchType==1，返回List<'HifiAlbum><br>
     *         searchType==5，返回List<'HifiMusic><br>
     *         searchType==0，返回<br>
     *         Map(key：artist,album,music对应value：List<'HifiArtist>,List<'HifiAlbum>,List<'HifiMusic>)
     */
    public T search(String apikey, int searchType, String key, PageDto page);

    /**
     * 根据关键字获取5个热词
     *
     * @param apikey
     * @param key
     *            关键字
     * @return
     */
    public List<String> get5HotspotsBySearchKey(String apikey, String key);
}
