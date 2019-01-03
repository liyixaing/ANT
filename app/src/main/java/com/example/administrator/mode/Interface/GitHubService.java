package com.example.administrator.mode.Interface;

import com.example.administrator.mode.Pojo.Common;
import com.example.administrator.mode.Pojo.Loginturn;
import com.example.administrator.mode.Pojo.ResponseBodytu;
import java.util.List;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface GitHubService {
    //注册第一步
    @FormUrlEncoded
    @POST("user/registerNewUser")
    Call<Common> register(
            @Field("phone") String phone,
            @Field("introducer") String introducer,
            @Field("worldCode") String worldCode,
            @Field("code") String code,
            @Field("phoneKey") String phoneKey,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //注册第二部
    @FormUrlEncoded
    @POST("user/registerSetLoginPassword")
    Call<Common> registertwo(
            @Field("phone") String phone,
            @Field("loginPassword") String loginPassword,
            @Field("worldCode") String worldCode,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //注册第三部
    @FormUrlEncoded
    @POST("user/registerSetTradePassword")
    Call<Common> registerthree(
            @Field("phone") String phone,
            @Field("tradePassword") String loginPassword,
            @Field("worldCode") String worldCode,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //登录
    @FormUrlEncoded
    @POST("sso/login")
    Call<Loginturn> login(
            @Field("phone") String phone,
            @Field("loginPassword") String loginPassword,
            @Field("phoneKey") String phoneKey,
            @Field("worldCode") String worldCode,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //修改登录密码
    @FormUrlEncoded
    @POST("user/modifyLoginPassword")
    Call<Common> loginpwdup(
            @Field("userId") String userId,
            @Field("oldLoginPassword") String oldLoginPassword,
            @Field("newLoginPassword") String newLoginPassword,
            @Field("token") String token,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //修改登录密码
    @FormUrlEncoded
    @POST("redPacket/claimEveryDayRedPacket")
    Call<Common> redPacketUp(
            @Field("userId") String userId,
            @Field("redPacketId") String redPacketId,
            @Field("token") String token,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //找回登录密码
    @FormUrlEncoded
    @POST("user/forgetLoginPassword")
    Call<Common> findpwd(
            @Field("worldCode") String worldCode,
            @Field("phone") String phone,
            @Field("code") String code,
            @Field("newLoginPassword") String newLoginPassword,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //修改用户信息
    @FormUrlEncoded
    @POST("user/updateUserInfo")
    Call<Common> userup(
            @Field("userId") String userId,
            @Field("avatar") String avatar,
            @Field("username") String username,
            @Field("token") String token,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //上传头像
    @Multipart
    @POST("user/doUploadAvatar")
    Call<ResponseBodytu> hadeup(
            @Query("userId") String userId,
            @Query("token") String token,
            @Part List<MultipartBody.Part> partList,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );
/*
    //长传图片
    @POST("user/doUpload")
    Call<ResponseBodytu> uploadMemberIcon(
    @Part List<MultipartBody.Part> partList);
*/

    //修改手机号码
    @FormUrlEncoded
    @POST("user/modifyPhone")
    Call<Common> phoneup(
            @Field("userId") String userId,
            @Field("oldPhone") String oldPhone,
            @Field("oldWorldCode") String oldWorldCode,
            @Field("newPhone") String newPhone,
            @Field("newWorldCode") String newWorldCode,
            @Field("code") String code,
            @Field("token") String token,
            @Field("tradePassword") String tradePassword,
            @Field("loginPassword") String loginPassword,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //分配接点
    @FormUrlEncoded
    @POST("antIntroducer/allotNode")
    Call<Common> antIntroduce(
            @Field("userId") String userId,
            @Field("token") String token,
            @Field("notBoundId") String notBoundId,
            @Field("insertPoint") String insertPoint,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    @FormUrlEncoded
    @POST("antShopPay/pay")
    Call<Common> antpay(
            @Field("user_id") String userId,
            @Field("user_token") String token,
            @Field("mall_key") String mall_key,
            @Field("order_no") String order_no,
            @Field("tradePassword") String tradePassword,
            @Field("reqType") String reqType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );
}
