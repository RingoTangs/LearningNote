package com.ymy.boot.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;


public class CodeGenerator {

    //数据源
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://39.97.3.60:3306/security?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "333";

    // 父包的全类名
    public static final String PACKAGE_PARENT = "com.ymy.boot";

    // 设置逻辑删除字段名字
    public static final String LOGIC_DELETE_FIELD_NAME = "deleted";

    // 乐观锁字段的名字
    public static final String VERSION_FILED_NAME = "version";

    // 自动填充的字段名字
    public static final String GMT_CREATE = "createTime";
    public static final String GMT_MODIFIED = "updateTime";

    // 数据库映射的表名 重点改这个！
    public static final String[] INCLUDE = {
            "menu",
            "menu_role",
            "role",
            "user",
            "user_role"
    };

    public static void main(String[] args) {
        // 1、创建代码生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig globalConfig = new GlobalConfig();

        // 设置代码的生成路径
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");

        // 设置作者
        globalConfig.setAuthor("Ringo");

        // 设置是否打开资源管理器
        globalConfig.setOpen(false);

        // 是否覆盖原来生成的
        globalConfig.setFileOverride(false);

        // 设置服务名字
        globalConfig.setServiceName("%sService");

        // 生成策略
        globalConfig.setIdType(IdType.ASSIGN_ID);

        // 日期类型
        globalConfig.setDateType(DateType.TIME_PACK);

        // 设置swagger
        globalConfig.setSwagger2(false);
        mpg.setGlobalConfig(globalConfig);

        // 2、配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        dataSourceConfig.setDbType(DbType.MYSQL);
        mpg.setDataSource(dataSourceConfig);

        // 3、包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PACKAGE_PARENT);
        mpg.setPackageInfo(packageConfig);

        // 4、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 数据库的表名 重点就是改这个！
        strategyConfig.setInclude(INCLUDE);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        // 设置逻辑删除字段名字
        strategyConfig.setLogicDeleteFieldName(LOGIC_DELETE_FIELD_NAME);
        // 设置自动填充
        TableFill gmtCreate = new TableFill(GMT_CREATE, FieldFill.INSERT);
        TableFill gmtModified = new TableFill(GMT_MODIFIED, FieldFill.INSERT_UPDATE);
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(gmtCreate);
        tableFillList.add(gmtModified);
        strategyConfig.setTableFillList(tableFillList);

        // 乐观锁配置
        strategyConfig.setVersionFieldName(VERSION_FILED_NAME);
        mpg.setStrategy(strategyConfig);

        // 执行代码生成器
        mpg.execute();
    }

}
