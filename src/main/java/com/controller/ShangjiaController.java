
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 商家
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shangjia")
public class ShangjiaController {
    private static final Logger logger = LoggerFactory.getLogger(ShangjiaController.class);

    private static final String TABLE_NAME = "shangjia";

    @Autowired
    private ShangjiaService shangjiaService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AddressService addressService;//收货地址
    @Autowired
    private CartService cartService;//购物车
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private ShangpinService shangpinService;//商品
    @Autowired
    private ShangpinChatService shangpinChatService;//用户咨询
    @Autowired
    private ShangpinCollectionService shangpinCollectionService;//商品收藏
    @Autowired
    private ShangpinCommentbackService shangpinCommentbackService;//商品评价
    @Autowired
    private ShangpinOrderService shangpinOrderService;//商品订单
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("商家".equals(role))
            params.put("shangjiaId",request.getSession().getAttribute("userId"));
        params.put("shangjiaDeleteStart",1);params.put("shangjiaDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = shangjiaService.queryPage(params);

        //字典表数据转换
        List<ShangjiaView> list =(List<ShangjiaView>)page.getList();
        for(ShangjiaView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShangjiaEntity shangjia = shangjiaService.selectById(id);
        if(shangjia !=null){
            //entity转view
            ShangjiaView view = new ShangjiaView();
            BeanUtils.copyProperties( shangjia , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shangjia:{}",this.getClass().getName(),shangjia.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ShangjiaEntity> queryWrapper = new EntityWrapper<ShangjiaEntity>()
            .eq("username", shangjia.getUsername())
            .or()
            .eq("shangjia_phone", shangjia.getShangjiaPhone())
            .andNew()
            .eq("shangjia_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangjiaEntity shangjiaEntity = shangjiaService.selectOne(queryWrapper);
        if(shangjiaEntity==null){
            shangjia.setShangjiaDelete(1);
            shangjia.setCreateTime(new Date());
            shangjia.setPassword(MD5Utils.md5("123456"));
            shangjiaService.insert(shangjia);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shangjia:{}",this.getClass().getName(),shangjia.toString());
        ShangjiaEntity oldShangjiaEntity = shangjiaService.selectById(shangjia.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(shangjia.getShangjiaPhoto()) || "null".equals(shangjia.getShangjiaPhoto())){
                shangjia.setShangjiaPhoto(null);
        }

            shangjiaService.updateById(shangjia);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShangjiaEntity> oldShangjiaList =shangjiaService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ShangjiaEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ShangjiaEntity shangjiaEntity = new ShangjiaEntity();
            shangjiaEntity.setId(id);
            shangjiaEntity.setShangjiaDelete(2);
            list.add(shangjiaEntity);
        }
        if(list != null && list.size() >0){
            shangjiaService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ShangjiaEntity> shangjiaList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ShangjiaEntity shangjiaEntity = new ShangjiaEntity();
//                            shangjiaEntity.setUsername(data.get(0));                    //账户 要改的
//                            //shangjiaEntity.setPassword("123456");//密码
//                            shangjiaEntity.setShangjiaName(data.get(0));                    //商家名称 要改的
//                            shangjiaEntity.setShangjiaPhone(data.get(0));                    //联系方式 要改的
//                            shangjiaEntity.setShangjiaEmail(data.get(0));                    //邮箱 要改的
//                            shangjiaEntity.setShangjiaPhoto("");//详情和图片
//                            shangjiaEntity.setShangjiaXingjiTypes(Integer.valueOf(data.get(0)));   //商家信用类型 要改的
//                            shangjiaEntity.setNewMoney(data.get(0));                    //现有余额 要改的
//                            shangjiaEntity.setShangjiaContent("");//详情和图片
//                            shangjiaEntity.setShangjiaDelete(1);//逻辑删除字段
//                            shangjiaEntity.setCreateTime(date);//时间
                            shangjiaList.add(shangjiaEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //联系方式
                                if(seachFields.containsKey("shangjiaPhone")){
                                    List<String> shangjiaPhone = seachFields.get("shangjiaPhone");
                                    shangjiaPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> shangjiaPhone = new ArrayList<>();
                                    shangjiaPhone.add(data.get(0));//要改的
                                    seachFields.put("shangjiaPhone",shangjiaPhone);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<ShangjiaEntity> shangjiaEntities_username = shangjiaService.selectList(new EntityWrapper<ShangjiaEntity>().in("username", seachFields.get("username")).eq("shangjia_delete", 1));
                        if(shangjiaEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShangjiaEntity s:shangjiaEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<ShangjiaEntity> shangjiaEntities_shangjiaPhone = shangjiaService.selectList(new EntityWrapper<ShangjiaEntity>().in("shangjia_phone", seachFields.get("shangjiaPhone")).eq("shangjia_delete", 1));
                        if(shangjiaEntities_shangjiaPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShangjiaEntity s:shangjiaEntities_shangjiaPhone){
                                repeatFields.add(s.getShangjiaPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shangjiaService.insertBatch(shangjiaList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        ShangjiaEntity shangjia = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("username", username));
        if(shangjia==null || !shangjia.getPassword().equals(MD5Utils.md5(password)))
            return R.error("账号或密码不正确");
        else if(shangjia.getShangjiaDelete() != 1)
            return R.error("账户已被删除");
        String token = tokenService.generateToken(shangjia.getId(),username, "shangjia", "商家");
        R r = R.ok();
        r.put("token", token);
        r.put("role","商家");
        r.put("username",shangjia.getShangjiaName());
        r.put("tableName","shangjia");
        r.put("userId",shangjia.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<ShangjiaEntity> queryWrapper = new EntityWrapper<ShangjiaEntity>()
            .eq("username", shangjia.getUsername())
            .or()
            .eq("shangjia_phone", shangjia.getShangjiaPhone())
            .andNew()
            .eq("shangjia_delete", 1)
            ;
        ShangjiaEntity shangjiaEntity = shangjiaService.selectOne(queryWrapper);
        if(shangjiaEntity != null)
            return R.error("账户或者联系方式已经被使用");
        shangjia.setPassword(MD5Utils.md5(shangjia.getPassword()));
        shangjia.setShangjiaXingjiTypes(1);
        shangjia.setNewMoney(0.0);
        shangjia.setShangjiaDelete(1);
        shangjia.setCreateTime(new Date());
        shangjiaService.insert(shangjia);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        ShangjiaEntity shangjia = shangjiaService.selectById(id);
        shangjia.setPassword(MD5Utils.md5("123456"));
        shangjiaService.updateById(shangjia);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        ShangjiaEntity shangjia = shangjiaService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!!MD5Utils.md5(oldPassword).equals(shangjia.getPassword())){
			return R.error("原密码输入错误");
		}
		if(!MD5Utils.md5(newPassword).equals(shangjia.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        shangjia.setPassword(MD5Utils.md5(newPassword));
		shangjiaService.updateById(shangjia);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        ShangjiaEntity shangjia = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("username", username));
        if(shangjia!=null){
            shangjia.setPassword(MD5Utils.md5("123456"));
            shangjiaService.updateById(shangjia);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrShangjia(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        ShangjiaEntity shangjia = shangjiaService.selectById(id);
        if(shangjia !=null){
            //entity转view
            ShangjiaView view = new ShangjiaView();
            BeanUtils.copyProperties( shangjia , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = shangjiaService.queryPage(params);

        //字典表数据转换
        List<ShangjiaView> list =(List<ShangjiaView>)page.getList();
        for(ShangjiaView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShangjiaEntity shangjia = shangjiaService.selectById(id);
            if(shangjia !=null){


                //entity转view
                ShangjiaView view = new ShangjiaView();
                BeanUtils.copyProperties( shangjia , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shangjia:{}",this.getClass().getName(),shangjia.toString());
        Wrapper<ShangjiaEntity> queryWrapper = new EntityWrapper<ShangjiaEntity>()
            .eq("username", shangjia.getUsername())
            .or()
            .eq("shangjia_phone", shangjia.getShangjiaPhone())
            .andNew()
            .eq("shangjia_delete", 1)
//            .notIn("shangjia_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangjiaEntity shangjiaEntity = shangjiaService.selectOne(queryWrapper);
        if(shangjiaEntity==null){
            shangjia.setShangjiaDelete(1);
            shangjia.setCreateTime(new Date());
            shangjia.setPassword(MD5Utils.md5("123456"));
        shangjiaService.insert(shangjia);

            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

}

