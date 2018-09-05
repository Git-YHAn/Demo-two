package com.bee.devops.admin.common.utils;

import com.bee.devops.admin.core.vo.request.DeployHostRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;

/**
 * excel工具类
 *
 * @author wanghuajie
 * @date 2018/7/13 16:10
 */
public class ExcelUtils {
    /**
     * 获取单元格值
     *
     * @param cell
     * @return
     */
    private static Object getValue(Cell cell) {
        Object value = null;
        if (cell != null) {
            CellType cellType = cell.getCellTypeEnum();
            switch (cellType) {
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    value = cell.getNumericCellValue();
                    break;
                case BLANK:
                    value = null;
                    break;
                default:
                    throw new RuntimeException("单元格内容异常");
            }
        }
        return value;
    }

    /**
     * 转换,不合要求的数据直接过滤
     *
     * @param book
     * @param userId
     * @return
     */
    public static List<DeployHostRequest> transition(Workbook book, long userId) {
        List<DeployHostRequest> list = new ArrayList<>();
        Sheet sheet = book.getSheetAt(0);
        for (int i = 0, j = sheet.getLastRowNum(); i < j; i++) {
            DeployHostRequest deployHostRequest = new DeployHostRequest();
            // 从第二行开始读取,即读取具体的服务器配置
            Row row = sheet.getRow(i + 1);
            // 服务器名称
            String assetsName = (String) getValue(row.getCell(0));
            if (StringUtils.isEmpty(assetsName)) {
                continue;
            }
            // 服务器状态
            int status = (int) (double)getValue(row.getCell(1));
            if (status > 1 || status < 0) {
                continue;
            }
            // 服务器类型
            int type = (int) (double)getValue(row.getCell(2));
            if (type > 2 || type < 0) {
                continue;
            }
            // CPU
            String cpu = (String) getValue(row.getCell(3));
            // 磁盘
            String disk = (String) getValue(row.getCell(4));
            // 服务器账号
            String account = (String) getValue(row.getCell(5));
            if (StringUtils.isEmpty(account)) {
                continue;
            }
            // 服务器密码
            Object password = getValue(row.getCell(6));
            String pass = "";
            if (password instanceof String) {
                pass = (String) password;
            } else if (password instanceof Double) {
                pass = String.valueOf(password);
            }

            if (StringUtils.isEmpty(pass)) {
                continue;
            }
            // 内网ip
            String innerIp = (String) getValue(row.getCell(7));
            // 存储器
            String memory = (String) getValue(row.getCell(8));
            // 操作系统
            String operateSystem = (String) getValue(row.getCell(9));
            // 外网IP
            String outerIp = (String) getValue(row.getCell(10));
            if (StringUtils.isNotEmpty(outerIp) && !outerIp.matches("^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$")) {
                continue;
            }
            if (StringUtils.isNotEmpty(innerIp) && !innerIp.matches("^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$")) {
                continue;
            }
            // ssh地址(IP)
            String sshAddress = (String) getValue(row.getCell(11));
            if (StringUtils.isNotEmpty(sshAddress) && !sshAddress.matches("^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$")) {
                continue;
            }
            // ssh端口(数字)
            int sshPort = (int) (double)getValue(row.getCell(12));
            deployHostRequest.setAssetsName(assetsName);
            deployHostRequest.setAssetsType(type);  //服务器类型ID(正整数)
            deployHostRequest.setCpu(cpu);
            deployHostRequest.setDisk(disk);
            deployHostRequest.setHostAccount(account);  //服务器连接账号(非空)
            deployHostRequest.setHostPassword(pass);
            deployHostRequest.setInnerIp(innerIp);
            deployHostRequest.setMemory(memory);
            deployHostRequest.setOperateSystem(operateSystem);
            //获取当前登录人员ID
            deployHostRequest.setOperateUserId(userId);
            deployHostRequest.setAssetsStatus(status);
            deployHostRequest.setOuterIp(outerIp);
            deployHostRequest.setSshAddress(sshAddress);
            deployHostRequest.setSshPort(String.valueOf(sshPort));
            deployHostRequest.setInitialStatus(0);
            //将每个服务器对象添加到集合中
            list.add(deployHostRequest);
        }
        return list;
    }
}
