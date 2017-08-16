package com.mjl.controller.POIExcel;

import com.mjl.model.Sign;
import com.mjl.model.User;
import com.mjl.service.SignService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

//@Controller注解用于标示本类为web层控制组件
@Controller
//@RequestMapping("/POIExcel")用于标定访问时对url位置
@RequestMapping("/POIExcel")
//在默认情况下springmvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
@Scope("prototype")

public class Push {
    @Autowired
    SignService signService;


    //导出接口
    public  void pushExcel(String path,List<Sign> signList){

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("签到记录");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("员工号");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("员工名");
        cell.setCellStyle(style);
        cell = row.createCell( 2);
        cell.setCellValue("日期");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("签到时间");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("签到状态");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("签退时间");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("签退状态");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("真实时间");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("请假时间");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("工作时间");
        cell.setCellStyle(style);

        for (int i = 0; i < signList.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            Sign sign = signList.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell(0).setCellValue(sign.getEmplId());
            row.createCell(1).setCellValue(sign.getEmplName());
            row.createCell(2).setCellValue(sign.getTime());
            row.createCell(3).setCellValue(sign.getLogin());
            row.createCell(4).setCellValue(sign.getLoginState());
            row.createCell(5).setCellValue(sign.getSignOut());
            row.createCell(6).setCellValue(sign.getTrueTime());
//            row.createCell(7).setCellValue();
//            row.createCell(8).setCellValue();

//            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format());
        }
        FileOutputStream fout=null;
        try
        {
            fout = new FileOutputStream(path);
            wb.write(fout);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if(fout!=null){
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @RequestMapping("/findAliAccountSumReport")
    public void findAliAccountSumReport(@RequestParam(required = false) Integer emplId, @RequestParam(required = false) String beginTime,
                                        @RequestParam(required = false) String endTime, HttpServletRequest request, HttpServletResponse response){
        User user=(User)request.getSession().getAttribute("user");
        List<Sign> signList;
        //生成的excel文件目录
        String filePath = request.getSession().getServletContext().getRealPath("")+ File.separator+"excel"+File.separator+"sign";
        File dir = new File(filePath);
        if(!dir.exists()) dir.mkdirs();
        if("".equals(beginTime)){
            beginTime=null;
        }
        if ("".equals(endTime)){
            endTime=null;
        }
        if (user.getEmplAdmin()>0){
            signList=signService.getSignByTimeAndId(beginTime,endTime,emplId);
        }else {
            signList=signService.getSignByTimeAndId(beginTime,endTime,user.getId());
        }
        String filename = "签到记录.xls";
        filePath+=File.separator+"sign.xls";
        pushExcel(filePath,signList);
        try {
            InputStream in = new FileInputStream(filePath);
            response.setContentType(request.getSession().getServletContext().getMimeType(filename));// MIME类型
            // 判断浏览器IE,火狐
            String ages = request.getHeader("user-agent");
            if (ages.contains("MSIE")) {
                filename = URLEncoder.encode(filename, "UTF-8");
            } else if (ages.contains("Firefox")) {
                filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                filename = URLEncoder.encode(filename, "UTF-8");
            }
            response.setHeader("Content-Disposition", "attachment;filename="+ filename);
            OutputStream out = response.getOutputStream();
            byte b[] = new byte[1024*5];
            while((in.read(b))!=-1){
                out.write(b);
            }
            out.flush();
            out.close();
            in.close();
            System.out.println(filePath);
            this.deleteFile(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteFile(File dir) {

    }


}
