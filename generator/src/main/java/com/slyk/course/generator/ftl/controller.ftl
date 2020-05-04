package com.slyk.course.${module}.controller.admin;

import com.slyk.course.server.bo.ResponseBo;
import com.slyk.course.server.dto.${Domain}Dto;
import com.slyk.course.server.dto.PageDto;
import com.slyk.course.server.service.${Domain}Service;
import com.slyk.course.server.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    public static final String BUSINESS_NAME = "${tableNameCn}";

    @Autowired
    private ${Domain}Service ${domain}Service;

    @GetMapping("")
    public ResponseBo<PageDto<${Domain}Dto>> get${Domain}List(PageDto<${Domain}Dto> pageDto) {
        try {
            ${domain}Service.get${Domain}List(pageDto);
            return ResponseBo
                    .<PageDto<${Domain}Dto>>builder()
                    .error_no(0)
                    .data(pageDto)
                    .msg("获取${tableNameCn}内容成功！")
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseBo
                    .<PageDto<${Domain}Dto>>builder()
                    .error_no(50000)
                    .data(pageDto)
                    .msg("获取${tableNameCn}内容失败，出现异常！")
                    .build();
        }
    }

    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBo<${Domain}Dto> saveOne${Domain}(@RequestBody ${Domain}Dto ${domain}Dto) {

        // 保存校验
        <#list fieldList as field>
            <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="sort">
                <#if !field.nullAble>
        ValidatorUtil.require(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}");
                </#if>
                <#if (field.length > 0)>
        ValidatorUtil.length(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}", 1, ${field.length?c});
                </#if>
            </#if>
        </#list>

        try {
            ${domain}Service.saveOne${Domain}(${domain}Dto);
            return ResponseBo
                    .<${Domain}Dto>builder()
                    .error_no(0)
                    .data(${domain}Dto)
                    .msg("保存成功！")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo
                    .<${Domain}Dto>builder()
                    .error_no(0)
                    .data(${domain}Dto)
                    .msg("保存失败！")
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseBo<Boolean> deleteOne${Domain}(@PathVariable("id") String id){
        try {
            ${domain}Service.deleteOne${Domain}(id);
            return ResponseBo
                    .<Boolean>builder()
                    .error_no(0)
                    .data(true)
                    .msg("删除成功！")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo
                    .<Boolean>builder()
                    .error_no(0)
                    .data(true)
                    .msg(e.getMessage())
                    .build();
        }
    }

}
