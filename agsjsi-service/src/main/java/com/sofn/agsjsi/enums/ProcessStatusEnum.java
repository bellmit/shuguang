package com.sofn.agsjsi.enums;

import com.google.common.collect.Lists;
import com.sofn.agsjsi.util.SysOwnOrgUtil;
import com.sofn.agsjsi.vo.DropDownVo;
import com.sofn.agsjsi.vo.SysOrgAndRegionVo;
import com.sofn.common.model.Result;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 审核流程的状态值
 */
@Getter
public enum ProcessStatusEnum {
    //0：保存；1：撤回；2：上报；3：市级退回；4：市级审核；5：省级退回；6：省级通过；7：总站退回；8：总站通过；9：专家批复
    STATUS_SAVE("0", "已保存"),
    STATUS_RECALL("1", "撤回"),
    STATUS_REPORT("2", "已上报"),
    STATUS_RETURN_FIRST("3", "市级退回"),
    STATUS_APPROVE_FIRST("4", "市级通过"),
    STATUS_RETURN_SECOND("5", "省级退回"),
    STATUS_APPROVE_SECOND("6", "省级通过"),
    STATUS_RETURN_THREE("7", "总站退回"),
    STATUS_APPROVE_THREE("8", "总站通过"),
    STATUS_APPROVE_EXPORT("9", "专家批复");
    private String status;
    private String defaultAdvice;

    ProcessStatusEnum(String status, String defaultAdvice) {
        this.status = status;
        this.defaultAdvice = defaultAdvice;
    }

    /**
     * 根据当前的账号，获取相应的状态值
     *
     * @return 下拉列表
     */
    public static List<DropDownVo> listForStatus() {
        List<DropDownVo> list = Lists.newArrayList();
        //获取当前的审核级别，根据不同的审核级别，获取不同的审核状态
        Result<SysOrgAndRegionVo> levelResult = SysOwnOrgUtil.getSysApproveLevelForApprove();
        if (Result.CODE.equals(levelResult.getCode())) {
            SysOrgAndRegionVo data = levelResult.getData();
            ProcessStatusEnum[] values = ProcessStatusEnum.values();

            Arrays.stream(values).forEach(key -> {
                DropDownVo dropDownVo = new DropDownVo();
                if (ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel().equals(data.getApproveLevel())) {
                    //不能审核的，显示所有状态
                    if ("0,2,3,4,5,6,7,8,9".contains(key.getStatus())) {
                        dropDownVo.setId(key.getStatus());
                        dropDownVo.setName(key.getDefaultAdvice());
                        list.add(dropDownVo);
                    }
                } else if (ApproveLevelEnum.APPROVE_FIRST_LEVEL.getLevel().equals(data.getApproveLevel())) {
                    //市级
                    if ("2,4,6,8,9".contains(key.getStatus())) {
                        dropDownVo.setId(key.getStatus());
                        dropDownVo.setName(key.getDefaultAdvice());
                        list.add(dropDownVo);
                    }
                } else if (ApproveLevelEnum.APPROVE_SECOND_LEVEL.getLevel().equals(data.getApproveLevel())) {
                    //省级
                    if ("4,6,8,9".contains(key.getStatus())) {
                        dropDownVo.setId(key.getStatus());
                        dropDownVo.setName(key.getDefaultAdvice());
                        list.add(dropDownVo);
                    }
                } else if (ApproveLevelEnum.APPROVE_THREE_LEVEL.getLevel().equals(data.getApproveLevel())) {
                    //总站
                    if ("6,8,9".contains(key.getStatus())) {
                        dropDownVo.setId(key.getStatus());
                        dropDownVo.setName(key.getDefaultAdvice());
                        list.add(dropDownVo);
                    }
                } else if (ApproveLevelEnum.APPROVE_FOUR_LEVEL.getLevel().equals(data.getApproveLevel())) {
                    //专家
                    if ("8,9".contains(key.getStatus())) {
                        dropDownVo.setId(key.getStatus());
                        dropDownVo.setName(key.getDefaultAdvice());
                        list.add(dropDownVo);
                    }
                }
            });
            return list;
        } else {
            return list;
        }

    }
}
