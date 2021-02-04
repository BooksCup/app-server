package com.bc.app.server.service.impl;

import com.bc.app.server.entity.FabricQcRecord;
import com.bc.app.server.entity.FabricQcWarehouse;
import com.bc.app.server.entity.Goods;
import com.bc.app.server.mapper.FabricQcRecordMapper;
import com.bc.app.server.mapper.FabricQcWarehouseMapper;
import com.bc.app.server.service.FabricQcWarehouseService;
import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.FabricQcWarehouseVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.PageResult;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.QcWarehouseSupplierIdVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 面料入库总记录
 */
@Service("fabricQcWarehouseService")
public class FabricQcWarehouseServiceImpl implements FabricQcWarehouseService {


    @Autowired
    private FabricQcWarehouseMapper fabricQcWarehouseMapper;

    @Autowired
    private FabricQcRecordMapper fabricQcRecordMapper;

    @Transactional
    @Override
    public boolean importExcel(MultipartFile file, String productName,
                               String productId, String supplierName,
                               String supplierId) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        int lastRowNum = xssfWorkbook.getSheetAt(0).getLastRowNum();//获取第一列的总行数
        FabricQcWarehouse fabricQcWarehouse = new FabricQcWarehouse();
        String warehouseId = CommonUtil.generateId();
        fabricQcWarehouse.setId(warehouseId);
        fabricQcWarehouse.setProductId(productId);
        fabricQcWarehouse.setProductName(productName);
        fabricQcWarehouse.setSupplierName(supplierName);
        fabricQcWarehouse.setSupplierId(supplierId);
        fabricQcWarehouse.setEnterpriseId("1");
        List<FabricQcRecord> fabricQcRecordList = new ArrayList<>();
        String cylinderNumber = "";
        for (int i = 0; i < lastRowNum; i++) {
            XSSFRow row = xssfWorkbook.getSheetAt(0).getRow(i);
            if (row == null) {
                continue;
            }
            XSSFCell cell = row.getCell(0);
            String titleStr = getString(cell).trim();
            XSSFCell cellContent = xssfWorkbook.getSheetAt(0).getRow(i).getCell(1);
            String contentStr = getString(cellContent).trim();
            if ("供应商".equals(titleStr)) {
                continue;
            }
            if ("缸号".equals(titleStr)) {
                fabricQcWarehouse.setCylinderNumber(contentStr);
                cylinderNumber = contentStr;
                continue;
            }
            if ("单号".equals(titleStr)) {
                fabricQcWarehouse.setOddNumber(contentStr);
                continue;
            }
            if ("日期".equals(titleStr)) {
                fabricQcWarehouse.setOddTime(contentStr);
                continue;
            }
            if ("品名(品种名称)".equals(titleStr)) {
                continue;
            }
            if ("门幅cm".equals(titleStr)) {
                fabricQcWarehouse.setWidth(contentStr);
                continue;
            }
            if ("克重g/㎡".equals(titleStr)) {
                fabricQcWarehouse.setGramWeight(contentStr);
                continue;
            }
            if ("颜色".equals(titleStr)) {
                fabricQcWarehouse.setColour(contentStr);
                continue;
            }
            if ("备注".equals(titleStr)) {
                fabricQcWarehouse.setRemark(contentStr);
                continue;
            }
            if ("总卷数".equals(titleStr)) {
                fabricQcWarehouse.setQcNumber(contentStr);
                continue;
            }
            if ("总重量".equals(titleStr)) {
                fabricQcWarehouse.setTotalWeight(contentStr);
                continue;
            }
            if ("总长度".equals(titleStr)) {
                fabricQcWarehouse.setTotalLength(contentStr);
                continue;
            }
            if ("序号".equals(titleStr)) {
                //获取当前所在行 列数
                int counmNum = xssfWorkbook.getSheetAt(0).getRow(i).getPhysicalNumberOfCells();
                String title1 = getString(xssfWorkbook.getSheetAt(0).getRow(i).getCell(0)).trim();
                String title2 = getString(xssfWorkbook.getSheetAt(0).getRow(i).getCell(1)).trim();
                String title3 = getString(xssfWorkbook.getSheetAt(0).getRow(i).getCell(2)).trim();
                for (int j = i + 1; j < lastRowNum; j++) {
                    FabricQcRecord fabricQcRecord = new FabricQcRecord();
                    fabricQcRecord.setId(CommonUtil.generateId());
                    fabricQcRecord.setWarehouseId(warehouseId);
                    fabricQcRecord.setCylinderNumber(cylinderNumber);
                    String sno = getString(xssfWorkbook.getSheetAt(0).getRow(j).getCell(0)).trim();
                    String weightBefore = getString(xssfWorkbook.getSheetAt(0).getRow(j).getCell(1)).trim();
                    String lengthBefore = getString(xssfWorkbook.getSheetAt(0).getRow(j).getCell(2)).trim();
                    fabricQcRecord.setSno((sno.replace(".", ";").split(";"))[0]);
                    fabricQcRecord.setWeightBefore(weightBefore);
                    fabricQcRecord.setLengthBefore(lengthBefore);
                    fabricQcRecordList.add(fabricQcRecord);
                    i = j;
                }
                continue;
            }
        }
        //保存总数据
        Integer integer = fabricQcWarehouseMapper.insertQcWarehouse(fabricQcWarehouse);
        //保存记录表信息
        Integer integer2 = fabricQcRecordMapper.insertList(fabricQcRecordList);
        if (integer > 0 && integer2 > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult<List<FabricQcWarehouse>> getPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<FabricQcWarehouse> page = fabricQcWarehouseMapper.getPage();
        return new PageResult<>(200, "查询成功", page.getResult(), page.getTotal());
    }

    /**
     * app盘点分页查询列表
     *
     * @param pageNum
     * @param pageSize
     * @param map
     */
    @Override
    public PageResult<List<FabricQcWarehouseVo>> getAppPage(Integer pageNum, Integer pageSize, Map<String, String> map) {

        List<FabricQcWarehouseVo> result = new ArrayList<>();
        Page<FabricQcWarehouseVo> fabricQcWarehouseVoPage = null;
        try {
            //数据处理  根据布料分组 /分页
            PageHelper.startPage(pageNum, pageSize);
            map.put("pageNum", pageNum + "");
            map.put("pageSize", pageSize + "");
            PageHelper.startPage(pageNum, pageSize);
            fabricQcWarehouseVoPage = fabricQcWarehouseMapper.getListGroupProductId(map);
            //数据处理  根据供应商分组
            result = fabricQcWarehouseVoPage.getResult();
            if (CollectionUtils.isNotEmpty(result)) {
                for (FabricQcWarehouseVo fabricQcWarehouseVo : result) {
                    String goodsId= fabricQcWarehouseVo.getProductId();
                    Map<String,String> goodMap=new HashMap<>();
                    goodMap.put("goodsId",goodsId);
                    Goods goods= fabricQcWarehouseMapper.getGoods(goodMap);
                    fabricQcWarehouseVo.setGoods(goods);
                    map.put("productId", fabricQcWarehouseVo.getProductId());
                    List<QcWarehouseSupplierIdVo> qcWarehouseSupplierIdVoList = fabricQcWarehouseMapper.getListGroupSupplierId(map);
                    //查询最里面一层
                    if (CollectionUtils.isNotEmpty(qcWarehouseSupplierIdVoList)) {
                        for (QcWarehouseSupplierIdVo qcWarehouseSupplierIdVo : qcWarehouseSupplierIdVoList) {
                            map.put("supplierId", qcWarehouseSupplierIdVo.getSupplierId());
                            List<FabricQcWarehouse> fabricQcWarehouseList = fabricQcWarehouseMapper.getAppPage(map);
                            qcWarehouseSupplierIdVo.setFabricQcWarehouseList(fabricQcWarehouseList);
                        }
                    }
                    fabricQcWarehouseVo.setQcWarehouseSupplierIdVos(qcWarehouseSupplierIdVoList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult<>(500, e.toString(), result, 0L);
        }
        return new PageResult<>(200, "查询成功", result, fabricQcWarehouseVoPage.getTotal());
    }


    @Override
    @Transactional
    public Integer delete(FabricQcWarehouse fabricQcWarehouse) {
        Integer integer = fabricQcRecordMapper.deleteByWarehouseId(fabricQcWarehouse.getId());
        Integer delete = fabricQcWarehouseMapper.update(fabricQcWarehouse);
        return delete;
    }


    public static String getString(XSSFCell xssfCell) {
        if (xssfCell == null) {
            return "";
        }
        if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else if (xssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else {
            return xssfCell.getStringCellValue();
        }
    }
}