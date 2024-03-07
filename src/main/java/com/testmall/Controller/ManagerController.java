package com.testmall.Controller;

import com.testmall.Model.Commodities;
import com.testmall.Model.Manager;
import com.testmall.Service.CommodityService;
import com.testmall.Service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/manager")
@Tag(name = "後臺管理相關API")
public class ManagerController {
    @Autowired
    ManagerService manService;

    @GetMapping("/getAll")
    @ResponseBody
    @Operation(summary = "取得所有管理員帳號資料，需要先登入")
    public List<Manager> getAll(){
        return manService.queryAll();
    }

    @GetMapping("/getById")
    @ResponseBody
    @Operation(summary = "取得所有管理員帳號資料(By id)，需要先登入")
    public Manager getById(Long id){
        return manService.queryById(id);
    }

    @GetMapping("/getByAccount")
    @ResponseBody
    @Operation(summary = "取得所有管理員帳號資料(By 帳號)，需要先登入")
    public Manager getByAccount(String account){
        return manService.queryByAccount(account);
    }

    @PostMapping("/createManager")
    @ResponseBody
    @Operation(summary = "建立管理員帳號，需要先登入，需要先登入")
    public String createManager(@RequestBody(required = false) Manager manager){
        return manService.createManager(manager);
    }

    @PostMapping("/updateManager")
    @ResponseBody
    @Operation(summary = "更新管理員帳號，需要先登入")
    public String updateManager(@RequestBody(required = false) Manager manager){
        return manService.updateManager(manager);
    }

    @PostMapping("/deleteManager")
    @ResponseBody
    @Operation(summary = "刪除管理員帳號，需要先登入")
    public String deleteManager(@RequestBody(required = false) List<Long> idList){
        return manService.deleteManager(idList);
    }

    @PostMapping("/Login")
    @ResponseBody
    @Operation(summary = "管理員登入")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登入成功"),
            @ApiResponse(responseCode = "401", description = "登入失敗"),
    })
    public String Login(HttpSession session, HttpServletResponse response, @RequestBody Manager manager){
        String str = "";
        switch(manService.Login(manager.getManAccount(), manager.getManPassword())){
            case 0:
                str = "登入成功!";
                session.setAttribute("manAccount", manager.getManAccount());
                break;
            case 1:
                str = "此帳號不存在!";
                response.setStatus(401);
                break;
            case 2:
                str = "密碼錯誤!";
                response.setStatus(401);
                break;
        }
        return str;
    }

    @GetMapping("/SignOut")
    @ResponseBody
    @Operation(summary = "管理員登出，需要先登入")
    public String SignOut(HttpSession session){
        //銷毀session中的資料
        session.removeAttribute("manAccount");
        return "登出成功";
    }
}
