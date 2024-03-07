package com.testmall.Controller;

import com.testmall.Model.Commodities;
import com.testmall.Model.Manager;
import com.testmall.Model.Userinfo;
import com.testmall.Service.UserinfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Controller
@RequestMapping("/user")
@Tag(name = "使用者相關API")
public class UserController {
    @Autowired
    UserinfoService userService;

    @GetMapping("/getAll")
    @ResponseBody
    @Operation(summary = "取得所有使用者資料")
    public List<Userinfo> getAll(){
        return userService.queryAll();
    }

    @GetMapping("/getByAccount")
    @ResponseBody
    @Operation(summary = "查詢使用者資料(By 帳號)")
    public Userinfo getByAccount(@Parameter(description = "帳號") @RequestParam String account){
        return userService.queryByAccount(account);
    }

    @PostMapping("/createUser")
    @ResponseBody
    @Operation(summary = "建立使用者帳號")
    public String createUser(@RequestBody(required = false) Userinfo userinfo){
        return userService.createUser(userinfo);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    @Operation(summary = "更新使用者帳號")
    public String updateUser(@RequestBody(required = false) Userinfo userinfo){
        return userService.updateUser(userinfo);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    @Operation(summary = "刪除使用者帳號")
    public String deleteUser(@RequestBody(required = false) List<String> accounts){
        return userService.deleteUser(accounts);
    }

    @PostMapping("/userLogin")
    @ResponseBody
    @Operation(summary = "使用者登入")
    public String userLogin(HttpServletResponse response, @RequestBody Userinfo user){
        String result = userService.userLogin(user.getUserAccount(), user.getUserPassword());
        if (result.equals("0001")){
            response.setStatus(401);
            return "無此帳號!";
        }
        else if (result.equals("0002")){
            response.setStatus(401);
            return "密碼錯誤!";
        }
        else
            return result;
    }

    @PostMapping("/userVerify")
    @ResponseBody
    @Operation(summary = "使用者登入驗證")
    public String userVerify(HttpServletRequest request){
        String authorHeader  = request.getHeader(AUTHORIZATION);
        String bearer ="Bearer ";
        String token = "";
        if (authorHeader != null && authorHeader.startsWith(bearer)) {
            token = authorHeader.substring(bearer.length()).replace(" ", "");
        }

        return userService.verifyUser(token.replace(" ", ""));
    }
}
