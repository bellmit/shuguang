package com.sofn.etl;

/** Created by helong on 17-5-23. */
public class TableEtlConfig {

  public static class Conn {
    public static class Setting {
      String name;
      String server;
      int port;
      String type;
      String username;
      String password;

      public String getName() {
        return name;
      }

      public int getPort() {
        return port;
      }

      public void setPort(int port) {
        this.port = port;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getServer() {
        return server;
      }

      public void setServer(String server) {
        this.server = server;
      }

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getUsername() {
        return username;
      }

      public void setUsername(String username) {
        this.username = username;
      }

      public String getPassword() {
        return password;
      }

      public void setPassword(String password) {
        this.password = password;
      }
    }

    Setting source = new Setting();
    Setting target = new Setting();

    public Setting getSource() {
      return source;
    }

    public void setSource(Setting source) {
      this.source = source;
    }

    public Setting getTarget() {
      return target;
    }

    public void setTarget(Setting target) {
      this.target = target;
    }
  }

  public static class Table {
    String tablename;

    public String getTablename() {
      return tablename;
    }

    public void setTablename(String tablename) {
      this.tablename = tablename;
    }
  }

  String name;

  Conn connection = new Conn();

  Table tableinput = new Table();
  Table tableoutput = new Table();

  public Table getTableinput() {
    return tableinput;
  }

  public void setTableinput(Table tableinput) {
    this.tableinput = tableinput;
  }

  public Table getTableoutput() {
    return tableoutput;
  }

  public void setTableoutput(Table tableoutput) {
    this.tableoutput = tableoutput;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Conn getConnection() {
    return connection;
  }

  public void setConnection(Conn connection) {
    this.connection = connection;
  }
}
