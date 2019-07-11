package com.example.administrator.mode.Interface;

import com.example.administrator.mode.Pojo.*;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MoneyService {
    //转账步骤一
    @FormUrlEncoded
    @POST("antBanance/transferAntPerpare")
    Call<Transferturn> transfer(
            @Field("userId") String userId,
            @Field("toPnone") String toPnone,
            @Field("toWorldCode") String toWorldCode,
            @Field("token") String token,
            @Field("amount") String amount,
            @Field("coinType") String coinType,
            @Field("memo") String memo,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //转账步骤二
    @FormUrlEncoded
    @POST("antBanance/transferAntCommit")
    Call<Transferturn> transfertwo(
            @Field("userId") String userId,
            @Field("tradeId") String tradeId,
            @Field("token") String token,
            @Field("tradePassword") String tradePassword,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign,
            @Field("secretKey") String secretKey
    );

    //兑换余额
    @FormUrlEncoded
    @POST("antBanance/banance2score")
    Call<Common> conversion(
            @Field("userId") String userId,
            @Field("amount") String tradeId,
            @Field("token") String token,
            @Field("tradePassword") String tradePassword,
            @Field("coinType") String coinType,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign,
            @Field("secretKey") String secretKey
    );

    //查询红包信息
    @GET("redPacket/getAntRedPackets")
    Call<Parckettrun> packet(
            @Query("userId") String userId,
            @Query("limit") String limit,
            @Query("token") String token,
            @Query("coinType") String coinType,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //查询好友
    @GET("antIntroducer/getMyIntroduce")
    Call<Friendtrun> getfriend(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("pageNum") String pageNum,
            @Query("pageSize") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //资产详情
    @GET("antScore/getAntLogScoreTop10")
    Call<prpertyturn> getproperty(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("pageNum") String pageNum,
            @Query("pageSize") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //资产详细
    @GET("antBanance/getAntBananceLogDetail")
    Call<PropertyPer> getAntBananceLogDetailPer(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("logId") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    @GET("sms/getWorldCodes")
    Call<CountryTurn> getWorldCodes(
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );


    @GET("notice/getNoticeByTop")
    Call<GetNoticeByTop> getNoticeByTop(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("offset") String offset,
            @Query("limit") String limit,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );


    @GET("notice/getNoticeDetail")
    Call<GetNoticeDetail> getNoticeDetail(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("noticeId") String noticeId,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );


    @GET("serverstate")
    Call<SystemUPTurn> serverstate();

    @POST("antShopPay/selectOrderDetail")
    Call<selectOrderDetail> selectOrder(
            @Query("user_id") String userId,
            @Query("user_token") String token,
            @Query("order_no") String order_no,
            @Query("mall_key") String mall_key,
            @Query("reqType") String reqType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //资产详细
    @GET("antScore/getAntLogScoreDetail")
    Call<PropertyPer> getAntLogScoreDetailPer(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("logId") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //资产详细
    @GET("antBanance/getAntBananceLogDetail")
    Call<PropertyStm> getAntBananceLogDetailStm(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("logId") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //资产详细
    @GET("antScore/getAntLogScoreDetail")
    Call<PropertyStm> getAntLogScoreDetailStm(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("logId") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //type :  2 , 3    dpos , node
    @GET("antBanance/getAntBananceLogList")
    Call<aa> getAntBananceLogList(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("type") String type,
            @Query("pageNum") String pageNum,
            @Query("pageSize") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    @GET("antBanance/getAntBananceLogList")
    Call<NodeTurn> getAntBananceNodeList(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("type") String type,
            @Query("pageNum") String pageNum,
            @Query("pageSize") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //pos  type 1
    @GET("antBanance/getAntBananceLogList")
    Call<PosTurn> getAntBanancepos(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("type") String type,
            @Query("pageNum") String pageNum,
            @Query("pageSize") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //获取接点信息
    @GET("antIntroducer/getInsertPointInfo")
    Call<GetNodeMessage> getNodeMessage(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("insertPoint") String insertPoint,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //节点收益
    @GET("antCapital/myNodeEarnings")
    Call<Earningtrun> getearning(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("pageNum") String pageNum,
            @Query("pageSize") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //通证查询
    @GET("antBanance/getAntBananceLogTop10")
    Call<Bantrun> getban(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("pageNum") String pageNum,
            @Query("pageSize") String pageSize,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign

    );

    //税率
    @GET("antBanance/getTransferAntInfo")
    Call<Paritiesturn> getParities(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //版本
    @GET("sat/getVersion")
    Call<Versionsturn> getVersion(
            @Query("model") String model,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //资金
    @GET("antCapital/getAntAccountInfo")
    Call<prturn> paroper(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //红包
    @GET("redPacket/getEveryDayRedPacket")
    Call<RedPacket> getEveryDayRed(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //公告
    @GET("notice/getLatestNotice")
    Call<LatestNoticeTurn> getLatestNotice(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    //余额换资金
    @GET("antBanance/getConvertScoreInfo")
    Call<Balanceturn> getbalan(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    @GET("antIntroducer/getMyNode")
    Call<MyNodeTurn> getMyNode(
            @Query("userId") String userId,
            @Query("token") String token,
            @Query("clientType") String clientType,
            @Query("timestamp") String timestamp,
            @Query("language") String language,
            @Query("sign") String sign
    );

    /*    //查询红包信息
        @GET("redPacket/getAntRedPackets")
        Call<Parckettrun.DataBean> packet(
                @Query("userId") String userId,
                @Query("limit") String limit,
                @Query("token") String token,
                @Query("coinType") String coinType
        );*/
    //领取一个红包
    @FormUrlEncoded
    @POST("redPacket/claimRedPackets")
    Call<Parckettrun> getpacket(
            @Field("userId") String userId,
            @Field("redPacketId") String redPacketId,
            @Field("token") String token,
            @Field("coinType") String coinType,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //领取全部红包
    @FormUrlEncoded
    @POST("redPacket/claimAllRedPackets")
    Call<Common> getpacketAll(
            @Field("userId") String userId,
            @Field("token") String token,
            @Field("language") String language,
            @Field("coinType") String coinType
    );

    //修改支付密码
    @FormUrlEncoded
    @POST("antAccount/modifyTradePassword")
    Call<Common> paypwdup(
            @Field("oldTradePassword") String oldTradePassword,
            @Field("token") String token,
            @Field("newTradePassword") String newTradePassword,
            @Field("userId") String userId,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //找回支付密码
    @FormUrlEncoded
    @POST("antAccount/forgetTradePassword")
    Call<Common> findpaypwd(
            @Field("worldCode") String worldCode,
            @Field("phone") String phone,
            @Field("token") String token,
            @Field("code") String code,
            @Field("newTradePassword") String newTradePassword,
            @Field("clientType") String clientType,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //手机验证码
    @FormUrlEncoded
    @POST("sms/send")
    Call<Common> sendssm(
            @Field("phoneNum") String phoneNum,
            @Field("worldCode") String worldCode,
            @Field("phoneKey") String phoneKey,
            @Field("loginType") String loginType,
            @Field("clientType") String clientType,
            @Field("type") String type,
            @Field("timestamp") String timestamp,
            @Field("language") String language,
            @Field("sign") String sign
    );

    //拉取红包配置信息
    @GET("antRedEnvelope/getEnvelopeConfigInfo")
    Call<GetRedBagMessage> getEnvelopeConfigInfo(
            @Query("userId") String userId,
            @Query("envelopeType") String envelopeType,
            @Query("timestamp") String timestamp,
            @Query("token") String token,
            @Query("sign") String sign,
            @Query("language") String language,
            @Query("clientType") String clientType
    );

    //发送单个红包
    @FormUrlEncoded
    @POST("antRedEnvelope/sendSingleEnvelope")
    Call<Common> sendSingleEnvelope(
            @Field("userId") String userId,
            @Field("tradePassword") String tradePassword,
            @Field("accept_id") String accept_id,
            @Field("accept_phone") String accept_phone,
            @Field("accept_wordCode") String accept_wordCode,
            @Field("envelope_type") String envelope_type,
            @Field("totoal_score") String totoal_score,
            @Field("memo") String memo,
            @Field("timestamp") String timestamp,
            @Field("token") String token,
            @Field("sign") String sign,
            @Field("language") String language,
            @Field("clientType") String clientType,
            @Field("secretKey") String secretKey

    );

    //发送单个红包
    @FormUrlEncoded
    @POST("antRedEnvelope/sendPeopleQuantityEnvelope")
    Call<Common> sendPeopleQuantityEnvelope(
            @Field("userId") String userId,
            @Field("tradePassword") String tradePassword,
            @Field("isFixedAmount") String isFixedAmount,
            @Field("quantity") String quantity,
            @Field("nation") String nation,
            @Field("province") String province,
            @Field("city") String city,
            @Field("totoal_score") String totoal_score,
            @Field("memo") String memo,
            @Field("timestamp") String timestamp,
            @Field("token") String token,
            @Field("sign") String sign,
            @Field("language") String language,
            @Field("clientType") String clientType,
            @Field("secretKey") String secretKey
    );

    //
    //领取单人红包
    @FormUrlEncoded
    @POST("antRedEnvelope/drawSingleEnvelope")
    Call<DrawSingleEnvelope> drawSingleEnvelope(
            @Field("userId") String userId,
            @Field("envelopeIds") String envelopeIds,
            @Field("timestamp") String timestamp,
            @Field("token") String token,
            @Field("sign") String sign,
            @Field("language") String language,
            @Field("clientType") String clientType
    );

    //领取多人红包
    @FormUrlEncoded
    @POST("antRedEnvelope/drawPeopleEnvelope")
    Call<GetRedBag> drawPeopleEnvelope(
            @Field("userId") String userId,
            @Field("longitude") String longitude,
            @Field("latitude") String latitude,
            @Field("timestamp") String timestamp,
            @Field("token") String token,
            @Field("sign") String sign,
            @Field("language") String language,
            @Field("clientType") String clientType
    );


    //创建钱包
    @FormUrlEncoded
    @POST("wallet/createWallet")
    Call<Common> createWallet(
            @Field("userId") String userId,
            @Field("wallet_name") String wallet_name,
            @Field("wallet_address") String wallet_address,
            @Field("pub_key") String pub_key,
            @Field("keystore") String keystore,
            @Field("timestamp") String timestamp,
            @Field("keystore_password") String keystore_password,
            @Field("token") String token,
            @Field("sign") String sign,
            @Field("language") String language,
            @Field("clientType") String clientType
    );

    //私钥登录
    @FormUrlEncoded
    @POST("sso/walletLogin")
    Call<Loginturn> walletLogin(
            @Field("sourceStr") String userId,
            @Field("signStr") String wallet_name,
            @Field("timestamp") String timestamp,
            @Field("token") String token,
            @Field("sign") String sign,
            @Field("language") String language,
            @Field("clientType") String clientType
    );

    //拉取单个红包列表
    @GET("antRedEnvelope/geSingleEnvelopeList")
    Call<geSingleEnvelopeList> geSingleEnvelopeList(
            @Query("userId") String userId,
            @Query("status") String status,
            @Query("offset") String offset,
            @Query("size") String size,
            @Query("timestamp") String timestamp,
            @Query("token") String token,
            @Query("sign") String sign,
            @Query("language") String language,
            @Query("clientType") String clientType
    );

    //拉取多人红包列表
    @GET("antRedEnvelope/getRedEnvelopeRecordList")
    Call<getRedEnvelopeRecordList> getRedEnvelopeRecordList(
            @Query("userId") String userId,
            @Query("isPrivate") String isPrivate,
            @Query("isSingle") String isSingle,
            @Query("offset") String offset,
            @Query("size") String size,
            @Query("timestamp") String timestamp,
            @Query("token") String token,
            @Query("sign") String sign,
            @Query("language") String language,
            @Query("clientType") String clientType
    );

    //拉取多人红包列表
    @GET("antRedEnvelope/getEnvelopeArea")
    Call<GetState> getEnvelopeArea(
            @Query("userId") String userId,
            @Query("timestamp") String timestamp,
            @Query("token") String token,
            @Query("sign") String sign,
            @Query("language") String language,
            @Query("clientType") String clientType
    );

    //拉取众筹列表
    @GET("zhongchou/getZhongChouList")
    Call<getZhongChouListBean> getZhongChouList(
            @Query("userId") String userId,
            @Query("timestamp") String timestamp,
            @Query("token") String token,
            @Query("sign") String sign,
            @Query("language") String language,
            @Query("clientType") String clientType,
            @Query("status") String status,
            @Query("sourceType") String sourceType,
            @Query("targetType") String targetType,
            @Query("offset") String offset,
            @Query("size") String size
    );

    //拉取我的众筹记录
    @GET("zhongchou/getMyZhongChouBuyRecord")
    Call<getMyZhongChouBuyRecordBean> getMyZhongChouBuyRecord(
            @Query("userId") String userId,
            @Query("timestamp") String timestamp,
            @Query("token") String token,
            @Query("sign") String sign,
            @Query("language") String language,
            @Query("clientType") String clientType,
            @Query("itemId") String itemId,
            @Query("offset") String offset,
            @Query("size") String size
    );

    //拉取众筹详细
    @GET("zhongchou/getZhongChouDetail")
    Call<getZhongChouDetailBean> getZhongChouDetail(
            @Query("userId") String userId,
            @Query("timestamp") String timestamp,
            @Query("token") String token,
            @Query("sign") String sign,
            @Query("language") String language,
            @Query("clientType") String clientType,
            @Query("itemId") String itemId
    );

    //拉取我的释放记录
    @GET("zhongchou/getZhongChouReleaseRecord")
    Call<getZhongChouReleaseRecordBean> getZhongChouReleaseRecord(
            @Query("userId") String userId,
            @Query("timestamp") String timestamp,
            @Query("token") String token,
            @Query("sign") String sign,
            @Query("language") String language,
            @Query("clientType") String clientType,
            @Query("itemId") String itemId,
            @Query("offset") String offset,
            @Query("size") String size
    );

    //认购 众筹
    @FormUrlEncoded
    @POST("zhongchou/buyZhongChou")
    Call<buyZhongChouBean> buyZhongChou(
            @Field("userId") String userId,
            @Field("timestamp") String timestamp,
            @Field("token") String token,
            @Field("sign") String sign,
            @Field("language") String language,
            @Field("clientType") String clientType,
            @Field("itemId") String itemId,
            @Field("amount") String amount,
            @Field("tradePassword") String tradePassword,
            @Field("secretKey") String secretKey
    );
}
