package com.bra.modules.pingPlusPlus;

import com.pingplusplus.exception.*;
import com.pingplusplus.model.Transfer;
import com.pingplusplus.model.TransferCollection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 该实例演示如何使用 Ping++ 进行企业转账。
 *
 * 开发者需要填写 apiKey ，openid 和 appId , apiKey 可以在 Ping++ 管理平台【应用信息里面查看】
 *
 * apiKey 有 TestKey 和 LiveKey 两种。
 *
 * TestKey 模式下不会产生真实的交易。
 *
 * openid 是发送红包的对象在公共平台下的openid ,获得 openid 的方法可以参考微信文档：http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html
 *
 */
public class TransferExample {

	private String appId;

	/**
	 * 接收者的 openid
	 */
	public static String openid ="USER_OPENID";

    public static void runDemos(String appId) {

        TransferExample transferExample = new TransferExample(appId);
        System.out.println("------- 创建 Transfer -------");
        Transfer transfer = transferExample.transfer();
        System.out.println("------- 查询 Transfer -------");
        transferExample.retrieve(transfer.getId());
        System.out.println("------- 查询 Transfer 列表 -------");
        transferExample.all();

    }

    TransferExample(String appId) {
        this.appId = appId;
    }

    /**
     * 创建企业转账
     *
     * 创建企业转账需要传递一个 map 给 Transfer.create();
     * map 填写的具体介绍可以参考：https://pingxx.com/document/api#api-t-new
     *
     * @return
     */
    public Transfer transfer() {
        Transfer transfer = null;
        String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + Main.randomString(7);
        Map<String, Object> transferMap = new HashMap<String, Object>();
        transferMap.put("channel", "wx_pub");
        transferMap.put("order_no", orderNo);
        transferMap.put("amount", "200");
        transferMap.put("type", "b2c");
        transferMap.put("currency", "cny");
        transferMap.put("recipient", openid);
        transferMap.put("description", "your description");
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", appId);
        transferMap.put("app", app);

        try {
            transfer = Transfer.create(transferMap);
            System.out.println(transfer);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
			e.printStackTrace();
		}
        return transfer;
    }

    /**
     * 根据 ID 查询
     *
     * 根据 ID 查询企业转账记录。
     * 参考文档：https://pingxx.com/document/api#api-t-inquiry
     * @param id
     */
    public void retrieve(String id) {
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            Transfer transfer = Transfer.retrieve(id, param);
            System.out.println(transfer);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
			e.printStackTrace();
		}

    }

    /**
     * 批量查询
     *
     * 批量查询企业转账记录，默认一次查询 10 条，用户可以使用 limit 自定义查询数目，但是最多不超过 100 条。
     */
    public void all() {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("limit", 3);

        try {
            TransferCollection transferCollection = Transfer.all(parm);
            System.out.println(transferCollection);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
			e.printStackTrace();
		}
    }
}
