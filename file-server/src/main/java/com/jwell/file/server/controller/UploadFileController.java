package com.jwell.file.server.controller;

import com.jwell.file.common.file.FileEnum;
import com.jwell.file.common.file.FileUtil;
import com.jwell.file.common.restful.RestfulVo;
import com.jwell.file.common.restful.ResultUtil;
import com.jwell.file.server.annotation.ApiVersion;
import com.jwell.file.server.domain.vo.FileDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


/***
 * 文件上传 controller
 */
@Slf4j
@RestController
public class UploadFileController extends BaseController {


    //文件保存路径
    @Value("${data.file.dir}")
    private String filePath;
    // 系统访问url
    @Value("${data.hostName}")
    private String hostName;

    /**
     * 单文件上传
     * @param file
     * @return
     */
    @PostMapping(value = "/upload/single",produces="application/json;charset=UTF-8")
    @ApiVersion(1)
    public RestfulVo singleUploadFile(@RequestParam("file") MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()) {
            return ResultUtil.error(500,"上传文件为空.");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        //得到文件后缀名
        String suffixName = FileUtil.getSuffixName(fileName).toUpperCase();
        //重命名文件名(避免文件名重复)
        String newFileName = FileUtil.getNewFileName(suffixName);
        //得到文件所属分类(文档、图片、视频,ZIP)
        FileEnum fileEnum = FileUtil.getFileTypeEnum(suffixName);
        //创建文件路径
        File dataFile = new File(getPath(fileEnum.name(),newFileName));
        //判断文件是否已经存在
        if (dataFile.exists()) {
            return ResultUtil.error(500,"上传文件已经存在.");
        }
        //判断文件父目录是否存在
        if (!dataFile.getParentFile().exists()) {
            dataFile.getParentFile().mkdir();
        }
        try {
            //上传文件到本地磁盘
            file.transferTo(dataFile);
            // 请求访问文件的 url 地址 url="http://域名/项目名/images/"+newFileName
            String requestUrl = this.getFileRequestUrl(fileEnum.name(),newFileName);
            // 数据入库
            FileDataVo data = new FileDataVo(1L,fileName,newFileName,requestUrl);
            return ResultUtil.success(data);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error(500,"上传文件出错.");
        }

    }


    /**
     * 批量上传文件
     * @param request
     * @return
     */
    @PostMapping(value = "/upload/batch",produces="application/json;charset=UTF-8")
    @ApiVersion(1)
    public RestfulVo batchUploadFile(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        //判断文件是否为空
        if(files.isEmpty()){
            return ResultUtil.error(500,"上传文件为空.");
        }

        String path = "F:/test" ;

        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if(file.isEmpty()){
                return ResultUtil.error(500,"上传文件为空.");
            }else{
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    e.printStackTrace();
                    return ResultUtil.error(500,"上传文件出错.");
                }
            }
        }
        return null;
    }

    /**
     * 文件路径
     * @param fileType 文件类型
     * @param fileName  文件名
     * @return
     */
    private String getPath(String fileType,String fileName){
        StringBuffer path = new StringBuffer(filePath);
        path.append("/").append(fileType);
        path.append("/").append(fileName);
        return FileUtil.convertFilePath(path.toString());
    }

    /**
     * 得到文件请求的url 地址
     * @param fileType  文件类型
     * @param fileName  文件名称
     * @return
     */
    private String getFileRequestUrl(String fileType,String fileName){
        StringBuffer requestUrl = new StringBuffer(hostName);
        requestUrl.append(fileType).append("/").append(fileName);
        return requestUrl.toString();
    }

}
