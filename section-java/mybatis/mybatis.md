## mybatis 持久层框架

### mybatis 核心对象
- SqlSessionFactory 该对象使用工厂模式创建
- SqlSession
- Mapper(dao)
- ConfigFile 配置文件,可以基于xml 配置和基于注解配置

### maven dependices
> 同时需要 mybatis mysql-connector-java 两个依赖

### 使用mybatis
- 需要一个主配置文件 mybatis-config.xml
- 创建多个
