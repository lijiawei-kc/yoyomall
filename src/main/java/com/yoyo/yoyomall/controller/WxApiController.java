package com.yoyo.yoyomall.controller;
import com.google.gson.Gson;
import com.yoyo.yoyomall.utils.ConstantPropertiesUtil;
import com.yoyo.yoyomall.utils.HttpClientUtils;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;





//等前台   User  增删改查完成之后再写





@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
//    /**
//     * @param code
//     * @param state
//     * @return
//     */
//    @GetMapping("callback")
//    public String callback(String code, String state){
//
//        //得到授权临时票据code
//        System.out.println(code);
//        System.out.println(state);
//
//        //从redis中将state获取出来，和当前传入的state作比较
//        //如果一致则放行，如果不一致则抛出异常：非法访问
//
//        //向认证服务器发送请求换取access_token
//        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
//                "?appid=%s" +
//                "&secret=%s" +
//                "&code=%s" +
//                "&grant_type=authorization_code";
//
//        String accessTokenUrl = String.format(baseAccessTokenUrl,
//                ConstantPropertiesUtil.WX_OPEN_APP_ID,
//                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
//                code);
//
//        String result = null;
//        try {
//            result = HttpClientUtils.get(accessTokenUrl);
//            System.out.println("accessToken=============" + result);
//        } catch (Exception e) {
//            throw new YoyoException(20001, "获取access_token失败");
//        }
//
//        //解析json字符串
//        Gson gson = new Gson();
//        HashMap map = gson.fromJson(result, HashMap.class);
//        String accessToken = (String)map.get("access_token");
//        String openid = (String)map.get("openid");
//
//        //查询数据库当前用用户是否曾经使用过微信登录
//        User user = UserService.getByOpenid(openid);
//        if(user == null){
//            System.out.println("新用户注册");
//
//            //访问微信的资源服务器，获取用户信息
//            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
//                    "?access_token=%s" +
//                    "&openid=%s";
//            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
//            String resultUserInfo = null;
//            try {
//                resultUserInfo = HttpClientUtils.get(userInfoUrl);
//                System.out.println("resultUserInfo==========" + resultUserInfo);
//            } catch (Exception e) {
//                throw new YoyoException(20001, "获取用户信息失败");
//            }
//
//            //解析json
//            HashMap<String, Object> mapUserInfo = gson.fromJson(resultUserInfo, HashMap.class);
//            String nickname = (String)mapUserInfo.get("nickname");
//            String headimgurl = (String)mapUserInfo.get("headimgurl");
//
//            //向数据库中插入一条记录
//            User = new user();
//            user.setNickname(nickname);
//            user.setOpenid(openid);
//            user.setAvatar(headimgurl);
//            userService.save(user);
//        }
//
//
//
//        return "redirect:http://localhost:3000";
//    }
}
