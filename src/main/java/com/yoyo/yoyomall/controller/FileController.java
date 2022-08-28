package com.yoyo.yoyomall.controller;


import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.sun.xml.internal.ws.util.JAXWSUtils;
import com.yoyo.yoyomall.utils.R;
import com.yoyo.yoyomall.utils.YoyoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manager/file")
@Api(value = "fastDFs分布式文件存储 -- 文件上传下载")
@CrossOrigin//跨域的意思
public class FileController {

    //FastFileStorageClient 直接注入就能用 fastdsf自带的
    @Resource
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 文件上传
     *
     * @return result
     */
    @ApiOperation("上传")

    @PostMapping ( "/upload")
    public R upload(@RequestPart("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        try {
            //这里msg返回的是上传后的图片存储位置getFullPath()获取文件位置
            //fastDFS storage存储节点路径  xxxxxx：服务器ip
            String FASTDFSSERVERIMAGE = "http://10.10.10.102:8888/";
            result.put("path", FASTDFSSERVERIMAGE + fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(),
                    FilenameUtils.getExtension(file.getOriginalFilename()), null).getFullPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new YoyoException(20001,"文件上传失败");
        }
        return R.ok().data(result);
    }

    /**
     * 文件删除
     *
     * @param path 路径
     * @return R
     */
    @GetMapping(value = "/delete")
    @ApiOperation("删除")
    public R delete(@RequestParam String path) {
        try {
            fastFileStorageClient.deleteFile(path);
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"文件删除失败，请检查文件路径");
        }
        return R.ok().msg("文件删除成功");
    }


    /**
     * 文件下载
     *
     * @param path 路径
     */
    @GetMapping(value = "/download")
    @ApiOperation("下载")
    public void downLoad(@RequestParam String path, HttpServletResponse response) throws IOException {
        //文件后缀
        String substring = path.substring(path.lastIndexOf(".") + 1);
        byte[] bytes = fastFileStorageClient.downloadFile(path.substring(0, path.indexOf("/")), path.substring(path.indexOf("/") + 1), new DownloadByteArray());
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("下载的文件.jpg", "UTF-8"));
        // 写出
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.write(bytes, outputStream);
    }
}
