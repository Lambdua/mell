package lt.school.mell.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * @author liangtao
 * @Date 2019/10/21
 * excel表的数据读取
 **/
public class POIUtils {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 根据excel版本，获取对应的workBook
     *
     * @return org.apache.poi.ss.usermodel.Workbook
     * @author liangtao
     * @date 2019/10/21
     **/
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) {  //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     *
     * @throws Exception
     */
    public static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
            throw new Exception("文件不是Excel");
        }
    }


    /**
     * 根据cell中数据类型返回
     *
     * @param cell
     * @return java.lang.Object
     * @author liangtao
     * @date 2019/10/22
     **/
    public static Object getValue(Cell cell) {
        Object obj = null;
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                obj = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_ERROR:
                obj = cell.getErrorCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            default:
                break;
        }
        return  obj;
    }


    /**
     * excel表的列为数据库中的行 数据转化。
     *
     * @param beginColumn 列数据开始
     * @param dataCount   共有多少列，对应数据库表的行
     * @param beginRowNum 数据行开始
     * @author liangtao
     * @date 2019/10/21
     **/
    public static List<List<String>> excel2ColumnData(int beginColumn, int beginRowNum, int dataCount, File file) throws Exception {
        List<List<String>> result = new ArrayList<>();

        checkExcelVaild(file);

        Workbook workbok = getWorkbok(new FileInputStream(file), file);

        Sheet sheet = workbok.getSheetAt(0);

        //实例化每一行表数据
        for (int j = 0; j < dataCount; j++) {
            result.add(new LinkedList<>());
        }

        for (Row row : sheet) {
            if (row.getRowNum() < beginRowNum) {
                continue;
            }

            int index = 0;
            for (Cell cell : row) {
                if (cell.getColumnIndex() < beginColumn) {
                    continue;
                }
                result.get(index++).add(getValue(cell) + "");
            }
        }
        return result;
    }


    /**
     * 用于excel的列为数据库表中的列
     *
     * @param beginRowNum   表数据开始行
     * @param beginColumNum 表数据开始列
     * @param file
     * @return 行数据的按顺序组装
     * @author liangtao
     * @date 2019/10/21
     **/
    public static List<List<String>> excel2LineData(int beginRowNum, int beginColumNum, File file) throws Exception {
        List<List<String>> result = new ArrayList<>();
        //检测文件的合法性
        checkExcelVaild(file);


        //获取工作本对象
        Workbook workbok = getWorkbok(new FileInputStream(file), file);

        //获取第一个sheet
        Sheet sheet = workbok.getSheetAt(0);
        int lastColumn = sheet.getRow(beginRowNum - 1).getLastCellNum();
//        System.out.println("getLastCellNUM为:" + lastColumn);

        //读取文件的
        for (Row row : sheet) {
            int index = 0;
            if (row.getRowNum() < beginRowNum) {
                //如果当前行不在数据范围内
                continue;
            }

            LinkedList<String> rowData = new LinkedList<>();


            for (int i = beginColumNum; i < lastColumn; i++) {
                Cell cell = row.getCell(i);
                rowData.add(getValue(cell) + "");
            }

            result.add(rowData);
        }

        return result;

    }

    /**
     * 用于封装成key,value形式
     *
     * @param beginRow  表数据开始的地方
     * @param columnNum
     * @param file
     * @author liangtao
     * @date 2019/10/22
     **/
    public static List<Map<String, String>> excel2ListAsLineData(int beginRow, int columnNum, File file) throws Exception {
        checkExcelVaild(file);

        List<Map<String, String>> result = new ArrayList<>();
        Workbook workbok = getWorkbok(new FileInputStream(file), file);
        Sheet sheet = workbok.getSheetAt(0);
        Row rowheader = sheet.getRow(beginRow);
        List<String> keyList = new ArrayList<>();
        for (Cell cell : rowheader) {
            Object value = getValue(cell);
            if (value == null) {
                value = getValue(sheet.getRow(beginRow - 1).getCell(cell.getColumnIndex()));
            }
            keyList.add((value + "").replaceAll("\n", ""));
        }
        beginRow++;
        //读取文件的
        for (Row row : sheet) {
            if (!isRowEmpty(row)) {


                int index = 0;
                if (row.getRowNum() < beginRow) {
                    //如果当前行不在数据范围内
                    continue;
                }
                Map<String, String> rowData = new HashMap<>();
                for (int i = columnNum; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    rowData.put(keyList.get(index++), getValue(cell) + "");
                }
                result.add(rowData);
            }
        }
        return result;
    }


    /**
     * 根据表数据库设计excle封装metaTable
     *
     * @author liangtao
     * @date 2019/10/25
     **/



    /**
     * 担保圈共享表单独解析
     *
     * @param file excel表
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     * @author liangtao
     * @date 2020/1/7
     **/
    public static List<Map<String, String>> excel2ListAsColumnData(Integer rowStart, Integer columnStart, Integer dataCount, File file) throws Exception {
        checkExcelVaild(file);
        List<Map<String, String>> result = new ArrayList<>();
        List<String> key = new ArrayList<>();
        key.add("类别");

        Workbook workbok = getWorkbok(new FileInputStream(file), file);
        Sheet sheet = workbok.getSheetAt(0);

        for (int i = rowStart + 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(1);
            if (getValue(cell) == null) {
                key.add(getValue(row.getCell(0)) + "");
            } else {
                key.add(getValue(cell) + "");
            }
        }
        List<List<String>> lists = excel2ColumnData(2, 4, dataCount, file);
        for (int i = 0; i < lists.size(); i++) {
            List<String> list = lists.get(i);
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < list.size(); j++) {
                map.put(key.get(j), list.get(j));
            }
            result.add(map);
        }
        return result;
    }


    public static List<Map<String, String>> excel2ListByDBGX(File file) throws Exception {
        checkExcelVaild(file);
        List<Map<String, String>> result = new ArrayList<>();
        Workbook workbok = getWorkbok(new FileInputStream(file), file);

        List<String> keyList = new ArrayList<>();
        keyList.add("企业一_组织结构代码");
        keyList.add("企业一_企业名称");
        keyList.add("企业二_组织结构代码");
        keyList.add("企业二_企业名称");
        keyList.add("企业三_组织结构代码");
        keyList.add("企业三_企业名称");
        keyList.add("企业四_组织结构代码");
        keyList.add("企业四_企业名称");
        keyList.add("企业五_组织结构代码");
        keyList.add("企业五_企业名称");

//        Sheet sheet0 = workbok.getSheetAt(0);
        for (int i = 0; i < workbok.getNumberOfSheets(); i++) {
            Sheet sheet = workbok.getSheetAt(i);
            for (Row row : sheet) {
                if (!isRowEmpty(row)) {
                    Map<String, String> rowData = new HashMap<>();
                    if (row.getRowNum() < 6) {
                        continue;
                    }
                    for (int j = 1; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        rowData.put(keyList.get(j - 1), getValue(cell) + "");
                    }
                    result.add(rowData);
                }
            }
        }
        return result;
    }


    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                return false;
            }
        }
        return true;
    }

    public static List<Map<String, String>> newExcel2ListAsLineData(int beginRow, int columnNum, File file) throws Exception {
        checkExcelVaild(file);

        List<Map<String, String>> result = new ArrayList<>();
        Workbook workbok = getWorkbok(new FileInputStream(file), file);
        Sheet sheet = workbok.getSheetAt(0);
        Row rowheader = sheet.getRow(beginRow);
        List<String> keyList = new ArrayList<>();
        for (Cell cell : rowheader) {
            Object value = getValue(cell);
            if (value == null) {
                value = getValue(sheet.getRow(beginRow - 1).getCell(cell.getColumnIndex()));
                keyList.add((value + "").replaceAll("\n", ""));
            } else {
                Cell prefix = sheet.getRow(beginRow - 1).getCell(cell.getColumnIndex());
                if (isMergedRegion(sheet, prefix.getRowIndex(), prefix.getColumnIndex())) {
                    String prefixStr = getMergedRegionValue(sheet, prefix.getRowIndex(), prefix.getColumnIndex());
//                    System.out.println(prefixStr + "_" + value);
                    keyList.add((prefixStr + "_" + value).replaceAll("\n", ""));
                } else {
                    keyList.add((value + "").replaceAll("\n", ""));
                }
            }
        }
        beginRow++;
        //读取文件的
        for (Row row : sheet) {
            if (!isRowEmpty(row)) {
                int index = 0;
                if (row.getRowNum() < beginRow) {
                    //如果当前行不在数据范围内
                    continue;
                }
                Map<String, String> rowData = new HashMap<>();
                for (int i = columnNum; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    rowData.put(keyList.get(index++), getValue(cell) + "");
                }
                result.add(rowData);
            }
        }
        return result;
    }


    /**
     * 获取合并单元格的值
     */
    public static String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {

                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getValue(fCell) + "";
                }
            }
        }

        return null;
    }


    /**
     * 判断指定的单元格是否是合并单元格
     *
     * @param sheet  工作表
     * @param row    行下标
     * @param column 列下标
     * @return
     */
    public static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }


  /*  public static void main(String[] args) throws Exception {
//        File file=new File( "C:\\Users\\liangtao\\Desktop\\莱商\\银监局报表\\山东银监局辖区新版客户风险共享报表数据201908\\21家行报表\\1\\新增表3：各行业主要财务指标情况表1908.xls");

        File file=new File( "C:\\Users\\liangtao\\Desktop\\莱商\\银监局报表\\山东银监局辖区新版客户风险共享报表数据201908\\21家行报表\\1\\新增表8：企业主要财务指标差异表1908.xls");
        List<Map<String, String>> list = newExcel2ListAsLineData(5, 0, file);
    }*/

}

