package com.example.mstarc.lovemoon.api;


import com.example.mstarc.lovemoon.bean.BannerModel;
import com.example.mstarc.lovemoon.bean.DigitalDatas;
import com.example.mstarc.lovemoon.bean.FoodString;
import com.example.mstarc.lovemoon.bean.HistoryBean;
import com.example.mstarc.lovemoon.bean.JokeJsonString;
import com.example.mstarc.lovemoon.bean.LookUpJson;
import com.example.mstarc.lovemoon.bean.NewJson;
import com.example.mstarc.lovemoon.bean.QQStringJson;
import com.example.mstarc.lovemoon.bean.QWYS;
import com.example.mstarc.lovemoon.bean.RefreshModel;
import com.example.mstarc.lovemoon.bean.SongJson;
import com.example.mstarc.lovemoon.bean.TodayOfHistoryString;
import com.example.mstarc.lovemoon.bean.WXJsonString;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface Engine {

    @GET("{itemCount}item.json")
    Call<BannerModel> fetchItemsWithItemCount(@Path("itemCount") int itemCount);

    @GET
    Call<List<RefreshModel>> loadContentData(@Url String url);

    //    http://api.juheapi.com/japi/toh?key=您申请的KEY&v=1.0&month=11&day=1
    @FormUrlEncoded
    @POST("toh")
    Call<String> loadHistoryTodayData(@Field("key") String key, @Field("v") String v, @Field("month") String month, @Field("day") String day);

    //    http://api.juheapi.com/japi/tohdet?key=您申请的KEY&v=1.0&id=4847
    @FormUrlEncoded
    @POST("tohdet")
    Call<HistoryBean> loadaHistoryDetailsData(@Field("key") String key, @Field("v") String v, @Field("id") String id);

    //    http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
    @FormUrlEncoded
    @POST("index")
    Call<NewJson> getNewJsonData(@Field("type") String type, @Field("key") String key);

    //    http://api.avatardata.cn/TangShiSongCi/Search?key=ab9bca16251a42b2ab17d0eb30324497&keyWord=秋兴
    @FormUrlEncoded
    @POST("Search")
    Call<SongJson> getSongJsonData(@Field("key") String key, @Field("keyWord") String keyWord);

    //    http://api.avatardata.cn/TangShiSongCi/LookUp?key=ab9bca16251a42b2ab17d0eb30324497&id=fbbc1ccd-fabb-4429-9277-e78bb46c0d25
    @FormUrlEncoded
    @POST("LookUp")
    Call<LookUpJson> getLookUpData(@Field("key") String key, @Field("id") String id);

    /**
     * 笑话大全
     *
     * @param key
     * @param page
     * @param sort
     * @param time
     * @return
     */
    //    http://api.avatardata.cn/Joke/QueryJokeByTime?key=fed861ee97e54e8e8ce6adcc4d61a846&page=2&rows=10&sort=asc&time=1418745237
    @FormUrlEncoded
    @POST("QueryJokeByTime")
    Call<JokeJsonString> getJokeData(@Field("key") String key, @Field("page") int page, @Field("sort") String sort, @Field("time") String time);

    //    http://v.juhe.cn/weixin/query?key=您申请的KEY

    /**
     * 微信精选
     */
    @FormUrlEncoded
    @POST("query")
    Call<WXJsonString> getWXData(@Field("key") String key, @Field("pno") int pno);


    //    http://japi.juhe.cn/rank/getRankType?key=aa2eaa0e9fc4cff96bd5585c41c3cca3
    @GET("getRankType")
    Observable<DigitalDatas> getDigitalDatas(@Query("key") String key);

    //    http://japi.juhe.cn/qqevaluate/qq?key=您申请的appKey&qq=295424589

    @GET("qq")
    Observable<QQStringJson> getQQdatas(@Query("key") String key, @Query("qq") String qq);

    /**
     * 奇闻异事
     */
//    http://api.avatardata.cn/QiWenNews/Query?key=7b96433a5e004cb19d5feb1c4b05e4e0&page=1&rows=10
    @GET("Query")
    Call<QWYS> getQWISData(@Query("key") String key, @Query("page") int page, @Query("rows") int rows);

    //    http://api.avatardata.cn/Cook/CookClass?key=b2c38f1080ef4ca4a5f68535c21b13e7&id=0
    @GET("CookClass")
    Call<FoodString> getFoodData(@Query("key") String key, @Query("id") String id);

    //    http://api.avatardata.cn/HistoryToday/LookUp?key=a56d940f21f642ef88de81f5682888c7&yue=1&ri=1&type=1&page=1

//   type 数据类型，1：国内国际大事件，2：民间事件包含部分国家大事件
    @GET("LookUp")
    Call<TodayOfHistoryString> getTodayData(@Query("key") String key,
                                            @Query("yue") String yue,
                                            @Query("ri") String ri,
                                            @Query("type") String type,
                                            @Query("page") int page);
}