package net.zdsoft.common.hifi.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.utils.HttpUtil;

/**
 * hifi对接接口api<br>
 * 接口数据返回格式，详见cms/doc/对接/hifi/接口数据/***
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2016年9月18日 下午2:30:59 $
 */
public class HifiApiUtil {

    public static String HIFI_DOMAIN;
    private static final String HIFI_ENCRYPT_KEY = "hellozhenxianwang";// hifi加密字符串key
    private static final String CHANNEL_NAME = "yinyuedashi";

    /**
     * 初始化HIFI接口域名<br>
     * 测试接口："http://if2.zhenxian.fm/interfacetest2"<br>
     * 正式接口："http://if2.zhenxian.fm/interface2"
     */
    static {
        if (StringUtils.isBlank(HIFI_DOMAIN)) {
            HIFI_DOMAIN = NetstudyConfig.getParam(BaseConstants.HIFI_DOMAIN);
        }
    }

    private static final String url_get_account = HIFI_DOMAIN + "/ws/content/thirdPartyLogin";
    private static final String url_get_home_columns = HIFI_DOMAIN + "/ws/tv/index";
    private static final String url_get_album_info = HIFI_DOMAIN + "/ws/content/album/detail";
    private static final String url_get_music_info = HIFI_DOMAIN + "/ws/content/music/detail";
    private static final String url_get_musiclist_info = HIFI_DOMAIN + "/ws/content/pack/detail";
    private static final String url_get_artist_info = HIFI_DOMAIN + "/ws/content/artist/detail";
    private static final String url_get_hotspots_by_searchKey = HIFI_DOMAIN + "/ws/content/keyfrequency";
    private static final String url_get_search = HIFI_DOMAIN + "/ws/content/searchproduct";
    private static final String url_get_artist_types = HIFI_DOMAIN + "/ws/content/artistgroup/list";
    private static final String url_get_artistList_by_type = HIFI_DOMAIN + "/ws/content/artistgroup/detail";
    private static final String url_get_content_by_type_id = HIFI_DOMAIN + "/ws/content/album/list";
    private static final String url_get_interest_by_type_id = HIFI_DOMAIN + "/ws/content/interested";
    private static final String url_get_newMonthlyProduct = HIFI_DOMAIN + "/ws/order/getNewMonthlyProduct";
    private static final String url_create_order = HIFI_DOMAIN + "/ws/order/createOrder";
    private static final String url_pay_order = HIFI_DOMAIN + "/pay/thirdPartyPayResultCall";

    /**
     * 18、支付hifi订单
     *
     * @param orderId
     *            订单号
     * @param tradeStateValue
     *            音乐网支付结果，固定值（支付成功："1"；支付失败："0"）
     *
     * @return 返回结果：0表示交易成功，1表示订单交易失败，-1表示签名错误，-2支付状态传值错误，-3调用支付接口失败
     * @throws Exception
     */
    public static int payHifiOrder(String orderId, int tradeStateValue) throws Exception {
        String tradeState = "";
        if (tradeStateValue == 1) {
            tradeState = "success";
        }
        else if (tradeStateValue == 0) {
            tradeState = "error";
        }
        else {
            return -2;
        }
        String timestamp = String.valueOf(new Date().getTime());
        String signature = Md5Util.process(CHANNEL_NAME + timestamp);
        // post提交
        Map<String, String> postParams = new HashMap<String, String>();// 需要POST提交的参数值
        postParams.put("timestamp", timestamp);
        postParams.put("signature", signature);
        postParams.put("tradeState", tradeState);
        postParams.put("orderId", orderId);
        String result = postForm(url_pay_order, postParams);
        JSONObject object = new JSONObject();
        if (StringUtils.isBlank(result)) {
            object.put("error_code", "error_payHifiOrder");
            object.put("error_value", "【调用HiFi接口失败-支付hifi订单】");
            return -3;
        }
        else {
            object = JSON.parseObject(result);
        }
        return object.getInteger("result");
    }

    /**
     * 17、创建hifi订单<br>
     * url?accountNo=1476948030434&apikey=1476948030434&productId=8732&terminaltype=20&version=new&signature=
     * pddsslrbs4INMet0TlXpMp6HIDQ=
     *
     * @param apikey
     * @param productId
     *            套餐id
     * @return 返回结果<br>
     *         result：生成订单号成功时为true,失败为false<br>
     *         orderId：HIFI生成的订单号，失败时为空<br>
     *         {"orderId":"14351995531251","result":true}
     * @throws Exception
     */
    public static JSONObject createHifiOrder(String apikey, String productId) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("accountNo", apikey);
        urlParams.put("apikey", apikey);
        urlParams.put("productId", productId);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");

        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_create_order, urlParams, pKey);
        String result = getString(url);
        if (StringUtils.isBlank(result)) {
            object.put("error_code", "error_createHifiOrder");
            object.put("error_value", "【调用HiFi接口失败-创建hifi订单】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 16、获取会员信息及VIP套餐详情<br>
     * url?accountNo=1474265419748&apikey=1474265419748&terminaltype=20&version=new&
     * signature=4B9WPk5R+KA/GnfeqmaHmvWePxg=
     *
     * @param apikey
     * @return
     * @throws Exception
     */
    public static JSONObject getMemberInfoAndNewMonthlyProduct(String apikey) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("accountNo", apikey);
        urlParams.put("apikey", apikey);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");

        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_newMonthlyProduct, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_newMonthlyProduct");
            object.put("error_value", "【调用HiFi接口失败-获取VIP套餐详情】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 15、查询感兴趣的专辑或单曲<br>
     * url?apikey=1474265419748&key=1420441208518&maxitems=20&searchtype=1&startitem=0&terminaltype=20&version=new&
     * signature=eP406fU5cZnafPowUsYobn5GXAI=
     *
     * @param apikey
     * @param searchType
     *            值为1查询专辑，5查询单曲
     * @param id
     *            查询专辑时传入albumId，查询单曲时传入trackId
     * @param maxitem
     *            搜索数目（分页条数）
     * @param startitem
     *            搜索开始索引（分页开始的偏移量，从0开始）
     * @return
     * @throws Exception
     */
    public static JSONArray getInterestByTypeAndId(String apikey, int searchType, String id, int maxitem, int startitem)
            throws Exception {
        JSONArray object = new JSONArray();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("searchtype", searchType);
        urlParams.put("key", id);
        urlParams.put("maxitems", maxitem);
        urlParams.put("startitem", startitem);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_interest_by_type_id, urlParams, pKey);
        String result = getString(url);
        if (StringUtils.isNotBlank(result)) {
            object = (JSONArray) JSON.parse(result);
        }
        return object;
    }

    /**
     * 14、获取16-bit/flac播放流媒体值<br>
     *
     * @param apikey
     * @param listenUrl
     * @return
     * @throws Exception
     */
    public static JSONObject getFlacPlayUrl(String apikey, String playUrl) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("accountNo", apikey);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(playUrl, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_flacPlayUrl");
            object.put("error_value", "【调用HiFi接口失败-获取HiFi高保真音乐播放地址】");
        }
        else {
            if ("该用户无权访问该高清文件".equals(result)) {
                object.put("error", result);
            }
            else if ("服务器错误".equals(result)) {
                object.put("error", result);
            }
            else {
                object = JSON.parseObject(result);
            }
        }
        return object;
    }

    /**
     * 13、获取MP3实际播放地址<br>
     * listenUrl?apikey=1474265419748&terminaltype=20&version=new&signature=qt8LPunh9Tg3pJS4IsMf6bG++rI=
     *
     * @param apikey
     * @param listenUrl
     * @return listenUrl?apikey=1474265419748&terminaltype=20&version=new&signature=qt8LPunh9Tg3pJS4IsMf6bG++rI=
     * @throws Exception
     */
    public static String getMusicListenUrl(String apikey, String listenUrl) throws Exception {
        String url = "";
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        url = createurl(listenUrl, urlParams, pKey);
        return url;
    }

    /**
     * 12、根据推荐位栏目id获取栏目"更多"接口（专辑列表）<br>
     * url?apikey=1474265419748&id=6&maxitems=20&startitem=0&terminaltype=20&type=2&version=new&signature=
     * klhOfJMgmqwPPsxuZEuCXZIgxIM=
     *
     * @param apikey
     * @param id
     *            推荐位栏目id
     * @param maxitem
     *            搜索数目（分页条数）
     * @param startitem
     *            搜索开始索引（分页开始的偏移量，从0开始）
     * @return
     * @throws Exception
     */
    public static JSONObject getAlbumListByMenuId(String apikey, String id, int maxitem, int startitem)
            throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("id", id);
        urlParams.put("type", 2);// type：根据menuid查专辑列表时请传type=2
        urlParams.put("maxitems", maxitem);
        urlParams.put("startitem", startitem);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");

        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_content_by_type_id, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_musiclist");
            object.put("error_value", "【调用HiFi接口失败-获取HiFi首页栏目专辑列表】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 11、根据演出者获取专辑列表<br>
     * url?apikey=1474265419748&id=7891&maxitems=20&startitem=0&terminaltype=20&type=3&version=new&signature=
     * CKOtS/aRhBY+HNbMm80h3JITgZQ=
     *
     * @param apikey
     * @param id
     *            演出者id
     * @param maxitem
     *            搜索数目（分页条数）
     * @param startitem
     *            搜索开始索引（分页开始的偏移量，从0开始）
     * @return
     * @throws Exception
     */
    public static JSONObject getAlbumListByArtistId(String apikey, String id, int maxitem, int startitem)
            throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("id", id);
        urlParams.put("type", 3);// type：根据艺人id查专辑列表时请传type=3
        urlParams.put("maxitems", maxitem);
        urlParams.put("startitem", startitem);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");

        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_content_by_type_id, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_musiclist");
            object.put("error_value", "【调用HiFi接口失败-获取HiFi演唱者专辑列表】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 10、根据演唱者分类获取演唱者列表<br>
     * url?apikey=1474265419748&id=117&maxitems=100&startitem=0&terminaltype=20&version=new&signature=
     * RYYOd15IoT573UvlbHL60TJasw0=
     *
     * @param apikey
     * @param id
     *            演唱者类型ID
     * @param maxitem
     *            搜索数目（分页条数）
     * @param startitem
     *            搜索开始索引（分页开始的偏移量，从0开始）
     * @return
     * @throws Exception
     */
    public static JSONObject getArtistListByType(String apikey, int id, int maxitem, int startitem) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("id", id);
        urlParams.put("maxitems", maxitem);
        urlParams.put("startitem", startitem);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");

        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_artistList_by_type, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_musiclist");
            object.put("error_value", "【调用HiFi接口失败-获取HiFi演唱者列表】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 9、获取演出者分类列表 <br>
     * url?apikey=1474265419748&terminaltype=20&version=new&signature=qt8LPunh9Tg3pJS4IsMf6bG++rI=
     *
     * @param apikey
     * @return
     * @throws Exception
     */
    public static JSONArray getArtistTypes(String apikey) throws Exception {
        JSONArray object = new JSONArray();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_artist_types, urlParams, pKey);
        String result = getString(url);
        if (StringUtils.isNotBlank(result)) {
            object = (JSONArray) JSON.parse(result);
        }
        return object;
    }

    /**
     * 8、根据关键字搜索（post提交）<br>
     * url?apikey=1474265419748&maxitem=5&searchtype=1&startitem=78&terminaltype=20&version=new&signature=
     * K6jK6z4E19H5k0WbegjILvn/ki4=
     *
     * @param apikey
     * @param searchType
     *            搜索类型（1搜索专辑，5搜索单曲，10搜索艺术家，0搜索全部）
     * @param maxitem
     *            搜索数目（分页条数）
     * @param startitem
     *            搜索开始索引（分页开始的偏移量，从0开始）
     * @param key
     *            post提交关键词
     * @return
     * @throws Exception
     */
    public static JSONObject search(String apikey, int searchType, int maxitem, int startitem, String key)
            throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        urlParams.put("searchtype", searchType);
        urlParams.put("startitem", startitem);
        urlParams.put("maxitem", maxitem);
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_search, urlParams, pKey);

        // post提交
        Map<String, String> postParams = new HashMap<String, String>();// 需要POST提交的参数值
        postParams.put("key", key);
        String result = postForm(url, postParams);
        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_search");
            object.put("error_value", "【调用HiFi接口失败-调用HiFi搜索功能】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 7、根据关键字获取相关热门前五个搜索关键字（post提交）<br>
     * url?apikey=1474265419748&terminaltype=20&version=new&signature=qt8LPunh9Tg3pJS4IsMf6bG++rI=
     *
     * @param apikey
     * @param key
     *            post提交关键词
     * @return
     * @throws Exception
     */
    public static JSONObject get5HotspotsBySearchKey(String apikey, String key) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_hotspots_by_searchKey, urlParams, pKey);

        // post提交
        Map<String, String> postParams = new HashMap<String, String>();// 需要POST提交的参数值
        postParams.put("key", key);
        String result = postForm(url, postParams);
        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_hotspot");
            object.put("error_value", "【调用HiFi接口失败-调用HiFi搜索热点词功能】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 6、获取演出者详情<br>
     * url?apikey=1474265419748&id=387&terminaltype=20&version=new&signature=WVu71T15a/qbwisOG1K1/FH1Mdo=
     *
     * @param apikey
     * @param id
     *            artistId
     * @return
     * @throws Exception
     */
    public static JSONObject getArtistInfo(String apikey, String id) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("id", id);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_artist_info, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_musiclist");
            object.put("error_value", "【调用HiFi接口失败-获取HiFi演唱者详情】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 5、获取主题详情<br>
     * url?apikey=1474265419748&id=1465897064692&terminaltype=20&version=new&signature=B0OmgPMpW9jhVF1HB41bamklMyw=
     *
     * @param apikey
     * @param id
     *            musicListId
     * @return
     * @throws Exception
     */
    public static JSONObject getMusiclistInfo(String apikey, String id) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("id", id);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_musiclist_info, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_musiclist");
            object.put("error_value", "【调用HiFi接口失败-获取HiFi主题详情】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 4、获取歌曲详情<br>
     * url?apikey=1474265419748&id=1364969446907&terminaltype=20&version=new&signature=1BeT5SPviKkm/XBQcqPb61YwpnU=
     *
     * @param apikey
     * @param id
     *            trackId
     * @return
     * @throws Exception
     */
    public static JSONObject getMusicInfo(String apikey, String id) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("id", id);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_music_info, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_track");
            object.put("error_value", "【调用HiFi接口失败-获取HiFi歌曲详情】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 3、获取专辑详情<br>
     * url?apikey=1474265419748&id=1364969446434&terminaltype=20&version=new&signature=JG15XK+R2gWZ7m7pThuppyN2PUE=
     *
     * @param apikey
     * @param id
     *            albumId
     * @return
     * @throws Exception
     */
    public static JSONObject getAlbumInfo(String apikey, String id) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("id", id);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_album_info, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_album");
            object.put("error_value", "【调用HiFi接口失败-获取HiFi专辑详情】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 2、获取首页栏目以及推荐位的接口<br>
     * url?apikey=1474265419748&terminaltype=20&version=new&signature=qt8LPunh9Tg3pJS4IsMf6bG++rI=
     *
     * @param apikey
     * @return
     * @throws Exception
     */
    public static JSONObject getHomeColumns(String apikey) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        urlParams.put("apikey", apikey);
        urlParams.put("terminaltype", "20");
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + apikey);
        String url = createurl(url_get_home_columns, urlParams, pKey);
        String result = getString(url);

        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_home");
            object.put("error_value", "【调用HiFi接口失败-获取HiFi首页栏目信息】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * 1、根据音乐网注册hifi账户（post提交）<br>
     * url?apikey=guest&protocolver=zx/1.1&terminaltype=20&timestamp=1474341669224&version=new&signature=
     * EcI3ohPYuq00NKMSTBcQUhQb1RU=
     *
     * @param new_uesrId
     *            用户id
     * @param avatarUrl
     *            头像路径
     * @param userRealName
     *            用户姓名
     *
     * @return {"result": 0, "avatarUrl": "http://file.chnmaster.com/upload/userAvatar/2016/09/18/51_photo.jpg",
     *         "userName": "洪旭", "accountNo": 1462787414337 } <br>
     *         result：返回状态，0代表成功并返回字段值 <br>
     *         accountNo：重要值，作为以下接口的apikey
     *
     * @throws Exception
     */
    public static JSONObject getHiFiAccountByUser(String new_uesrId, String avatarUrl, String userRealName)
            throws Exception {
        JSONObject object = new JSONObject();
        Map<String, Object> urlParams = new HashMap<String, Object>();// 需要签名的参数值
        // 组装url并签名
        urlParams.put("apikey", "guest");
        urlParams.put("protocolver", "zx/1.1");
        urlParams.put("terminaltype", 20);
        urlParams.put("timestamp", new Date().getTime());
        urlParams.put("version", "new");
        String pKey = Md5Util.process(HIFI_ENCRYPT_KEY + "guest");
        String url = createurl(url_get_account, urlParams, pKey);

        // post提交
        Map<String, String> postParams = new HashMap<String, String>();// 需要POST提交的参数值
        postParams.put("uniqueId", new_uesrId);
        postParams.put("avatarUrl", avatarUrl);
        postParams.put("userName", URLEncoder.encode(userRealName, "UTF-8"));
        String result = postForm(url, postParams);
        if (StringUtils.isBlank(result)) {
            object.put("error_code", "none_account");
            object.put("error_value", "【调用HiFi接口失败-注册HiFi账户】");
        }
        else {
            object = JSON.parseObject(result);
        }
        return object;
    }

    /**
     * hifi获取内容<br>
     * 提醒：在http header传入channel=yinyuedashi（或与我们约定的渠道名称以便分渠道运营音乐数据）
     *
     * @param url
     * @return
     * @throws Exception
     */
    private static String getString(String url) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("channel", CHANNEL_NAME);
        return HttpUtil.getString(url, headers);
    }

    /**
     * hifi提交表单<br>
     * 提醒：在http header传入channel=yinyuedashi（或与我们约定的渠道名称以便分渠道运营音乐数据）
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    private static String postForm(String url, Map<String, String> params) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("channel", CHANNEL_NAME);
        return HttpUtil.postForm(url, params, headers);
    }

    /**
     * 获得客户端请求的完整的url链接
     *
     * @param url
     *            接口url
     * @param params
     *            接口参数（需要签名的参数）
     * @param pKey
     *            进行签名的key <br>
     *            加密（指定字符串+accountNo）：Md5Util.process("hellozhenxianwang"+accountNo)
     *
     * @return 形式（url?paramsStr&signature=签名）：<br>
     *         http://if2.zhenxian.fm/interfacetest2/ws/content/thirdPartyLogin?
     *         apikey=guest&protocolver=zx/1.1&terminaltype=20& timestamp=1472197985428
     *         &signature=fUq51+gybzfDmC+durKgS9Gyyq4=
     *
     * @throws IllegalStateException
     * @throws UnsupportedEncodingException
     */
    public static String createurl(String url, Map<String, Object> params, String pKey)
            throws IllegalStateException, UnsupportedEncodingException {
        // 1、去除数组中空值+排序组装参数字符串
        String paramUrl = getUrlParamsStr(filter(params, ""));
        // 2、过滤参数中的特殊字符
        String pStringToSign = stringFilter(paramUrl);
        // 3、生成签名
        String signatureStr = generator(pStringToSign, pKey);
        // 4、组装成完整的url链接
        StringBuffer sb = new StringBuffer(url);
        String sign_url = "&signature=" + signatureStr;
        sb.append("?").append(paramUrl).append(sign_url);
        return sb.toString();
    }

    /**
     * 生成签名算法
     *
     * @param pStringToSign
     *            需要签名的参数，通过排序且过滤特殊字符 形成的字符串连接
     * @param pKey
     *            进行签名的key <br>
     *            加密（指定字符串+accountNo）：Md5Util.process("hellozhenxianwang"+accountNo)
     *
     * @return API签名
     */
    public static String generator(String pStringToSign, String pKey)
            throws IllegalStateException, UnsupportedEncodingException {
        String lSignature = "None";
        try {
            Mac lMac = Mac.getInstance("HmacSHA1");
            SecretKeySpec lSecret = new SecretKeySpec(pKey.getBytes(), "HmacSHA1");
            lMac.init(lSecret);

            byte[] lDigest = lMac.doFinal(pStringToSign.getBytes());
            lSignature = new String(Base64Util.encode(lDigest));
        }
        catch (NoSuchAlgorithmException lEx) {
            throw new RuntimeException("Problems calculating HMAC", lEx);
        }
        catch (InvalidKeyException lEx) {
            throw new RuntimeException("Problems calculating HMAC", lEx);
        }
        return lSignature;
    }

    /**
     * 签名过滤参数中的特殊字符
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\s*|\t|\r|\n]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 将url参数map组 组装成 urlParamsStr（对参数进行排序）
     *
     * @param params
     *
     * @return paramA=valueA&paramB=valueB&paramC=valueC...<br>
     *         参数的顺序按照字母顺序排序进行签名<br>
     *         返回的字符串用于后面的签名
     *
     * @throws UnsupportedEncodingException
     */
    public static String getUrlParamsStr(Map<String, Object> params) {
        if (isNull(params)) {
            return "";
        }
        List<String> keys = new ArrayList<String>(params.keySet());// 将key转换为list
        Collections.sort(keys);// 将key进行排序
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            sb.append(key).append("=").append(params.get(key)).append("&");
            // try {
            // sb.append(key).append("=").append(URLEncoder.encode((params.get(key).toString()), "UTF-8")).append("&");
            // }
            // catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            // }
        }
        String paramUrl = sb.deleteCharAt(sb.lastIndexOf("&")).toString();
        return paramUrl;
    }

    /**
     * 除去数组中的空值和签名参数后的新签名参数组
     *
     * @param params
     * @param filterKey
     *            需过滤的Key
     * @return
     */
    public static Map<String, Object> filter(Map<String, Object> params, String filterKey) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (isNull(params)) {
            return result;
        }
        Object value = "";
        for (String key : params.keySet()) {
            value = params.get(key);
            if (value == null || "".equals(value) || key.equalsIgnoreCase(filterKey)) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 判断map是否为空
     *
     * @param map
     * @return boolean
     */
    public static boolean isNull(Map<?, ?> map) {
        return map == null || map.size() <= 0;
    }

    public static void main(String[] args) throws Exception {
        JSONObject obj = null;
        // 1、注册（post提交）
        // String uuid = "51";
        // String avatarUrl = "http://file.chnmaster.com/upload/userAvatar/2016/09/18/51_photo.jpg";
        // String userRealName = "hongx";
        // obj = getHiFiAccountByUser(uuid, avatarUrl, userRealName);
        // System.out.println(obj);
        // System.out.println(obj.get("accountNo"));

        // 测试环境：1476948030434
        // 外网环境：1480039935918
        String apikey = "1480039935918";

        // 2、首页
        // obj = getHomeColumns(apikey);
        // System.out.println(obj);

        // 12、根据推荐位栏目id获取栏目"更多"接口（专辑列表）
        // obj = getAlbumListByMenuId(apikey, "6", 1000, 0);
        // System.out.println(obj);

        // 3、专辑详情 外网1455764000977
        // obj = getAlbumInfo(apikey, "1455764000977");
        // System.out.println(obj);

        // 4、获取歌曲详情 外网1455815793014
        // obj = getMusicInfo(apikey, "1455815793014");
        // System.out.println(obj);

        // 5、获取主题详情
        // obj = getMusiclistInfo(apikey, "1465897064692");
        // System.out.println(obj);

        // 6、获取艺术家详情
        // obj = getArtistInfo(apikey, "7891");
        // System.out.println(obj);

        // 7、根据关键字获取相关热门前五搜索关键字（post提交）
        // obj = get5HotspotsBySearchKey(apikey, "cyx");
        // System.out.println(obj);

        // 8、根据关键字搜索（post提交）
        // obj = search(apikey, 0, 5, 0, "阿");
        // System.out.println(obj);
        // System.out.println(obj.get("list"));
        // obj = search(apikey, 10, 5, 0, "一场游戏一场梦");
        // System.out.println(obj);

        // 9、获取演出者分类列表
        // JSONArray a = getArtistTypes(apikey);
        // System.out.println(a);
        // System.out.println(a.getJSONObject(0).get("id"));

        // 10、根据演唱者分类获取演唱者列表信息
        // obj = getArtistListByType(apikey, 117, 5, 10);
        // System.out.println(obj);

        // 11、根据艺人获取专辑列表
        // obj = getAlbumListByArtistId(apikey, "2349", 20, 0);
        // System.out.println(obj);

        // 13、获取MP3实际播放地址 外网http://mg2.zhenxian.fm/media/access/audio/1455771796044
        String url = getMusicListenUrl(apikey, "http://mg2.zhenxian.fm/media/access/audio/1455771796044");
        System.out.println(url);

        // 14、获取16-bit/flac播放流媒体值
        // obj = getFlacPlayUrl(apikey, "http://mg2.zhenxian.fm/mediatest2/access/stream/audio/1364968014117");
        // System.out.println(obj);

        // 15、查询感兴趣的专辑或单曲
        // JSONArray array1 = getInterestByTypeAndId(apikey, 1, "", 20, 0);
        // System.out.println(array1);
        // JSONArray array2 = getInterestByTypeAndId(apikey, 5, "", 20, 0);
        // System.out.println(array2);

        // 16、获取会员信息及VIP套餐详情
        // obj = getMemberInfoAndNewMonthlyProduct(apikey);
        // System.out.println(obj);

        // 17、创建hifi订单
        // obj = createHifiOrder(apikey, "8732");
        // System.out.println(obj);

        // 18、支付hifi订单
        // int i = payHifiOrder(apikey, 1);
        // System.out.println(i);

    }
}
