package com.testmall.Controller;

import com.testmall.Model.Commodities;
import com.testmall.Model.Manager;
import com.testmall.Model.Userinfo;
import com.testmall.Service.UserinfoService;
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
public class UserController {
    @Autowired
    UserinfoService userService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Userinfo> getAll(){
        return userService.queryAll();
    }

    @GetMapping("/getByAccount")
    @ResponseBody
    public Userinfo getByAccount(@RequestParam String account){
        return userService.queryByAccount(account);
    }

    @PostMapping("/createUser")
    @ResponseBody
    public String createUser(@RequestBody(required = false) Userinfo userinfo){
        return userService.createUser(userinfo);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody(required = false) Userinfo userinfo){
        return userService.updateUser(userinfo);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestBody(required = false) List<String> accounts){
        return userService.deleteUser(accounts);
    }

    @PostMapping("/userLogin")
    @ResponseBody
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
