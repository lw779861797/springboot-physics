package com.physics.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.physics.mapper.GoodsReplyMapper;
import com.physics.pojo.Goods;
import com.physics.pojo.GoodsReply;
import com.physics.pojo.Student;
import com.physics.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GoodsController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    GoodsReplyMapper goodsReplyMapper;
    @Autowired
    GoodsService goodsService;
//    跳转到查看丢失物品的页面前要先登入
    @RequestMapping("/queryGoodsLogin")
    public String queryGoodsLogin(HttpServletRequest request){
        request.setAttribute("flag",4);
        return "login";
    }

    @RequestMapping("/queryGoods")
    public String queryGoods(@RequestParam(defaultValue = "1") Integer pageNum, Model model){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, 8);
        //startPage后紧跟的这个查询就是分页查询
        List<Goods> goodss= goodsService.queryGoods();
        PageInfo pageInfo = new PageInfo<Goods>(goodss);
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Goods goods : goodss){
            if(goods.getStatus() == 0){
                goods.setType("[认领]");
            }else {
                goods.setType("[找回]");
            }
            String time=format.format(goods.getDate());
            goods.setTime(time);
//            加入评论
            List<GoodsReply> goodsReplies=goodsReplyMapper.queryGoodsReplyById(goods.getId());
            goods.setGoodsReplyList(goodsReplies);
            for(GoodsReply goodsReply : goods.getGoodsReplyList()) {
                String reply_time=format.format(goodsReply.getDate());
                goodsReply.setTime(reply_time);
            }
        }
        model.addAttribute("pageInfo", pageInfo);
        return "lostAndFound_view";
    }
    @RequestMapping("/addGoodsLogin")
    public String addGoodsLogin(HttpServletRequest request){
        request.setAttribute("flag",3);
        return "login";
    }
    @RequestMapping("/addGoods")
    public String addGoods(HttpSession session,
                            @RequestParam("goods_title")String title,
                           @RequestParam("goods_context")String context,
                           @RequestParam("goods_selected")String selected){
        Student student= (Student) session.getAttribute("students");
        Goods goods=new Goods();
        goods.setAdmin_id(student.getId());
        goods.setDate(new Date());
        goods.setStatus(Integer.valueOf(selected));
        goods.setContext(context);
        goods.setTitle(title);
        goods.setAdmin_message(student.getName()+'('+student.getUser_name()+')');
        goodsService.insert(goods);
        return "redirect:queryGoods";
    }
//    新增加物品信息
    @RequestMapping("/addGoodsReply")
    public String addGoodsReply(HttpSession session,
                                @RequestParam("goodsreply_id")String id,
                                @RequestParam("goodsreply_context")String context){
        Student student= (Student) session.getAttribute("students");
        if(student == null){
            logger.warn("增加物品丢失页面 :用户信息为空");
            return "redirect:/base";
        }
        int goods_id=Integer.valueOf(id);
        Goods goods=goodsService.queryGoodsByGoodId(goods_id);
        if(goods == null){
            logger.error("学生ID为: "+student.getId()+
                    " 输入的丢失物品帖序号: "+goods_id
            +"找不到");
            return "redirect:queryGoods";
        }
        GoodsReply goodsReply=new GoodsReply();
        goodsReply.setDate(new Date());
        goodsReply.setAdmin_id(student.getId());
        goodsReply.setContext(context);
        goodsReply.setGoods_id(goods_id);
        goodsReply.setAdmin_message(student.getName()+'('+student.getUser_name()+')');
        goodsReplyMapper.insert(goodsReply);
        return "redirect:queryGoods";
    }
}
