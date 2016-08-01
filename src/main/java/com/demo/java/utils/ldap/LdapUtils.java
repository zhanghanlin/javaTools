package com.demo.java.utils.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Ldap连接AD
 */
public class LdapUtils {

    DirContext dc = null;
    static String LDAP_URL = "ldap://192.168.1.1"; // LDAP访问地址
    static String adminName = "test@test.com"; // 注意用户名的写法：domain\User或
    static String adminPassword = "test"; // 密码

    /**
     * Ldap连接
     *
     * @return LdapContext
     */
    public void init() {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LDAP_URL);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, adminName);
        env.put(Context.SECURITY_CREDENTIALS, adminPassword);
        try {
            dc = new InitialDirContext(env);// 初始化上下文
            System.out.println("认证成功");// 这里可以改成异常抛出。
        } catch (javax.naming.AuthenticationException e) {
            System.out.println("认证失败");
        } catch (Exception e) {
            System.out.println("认证出错：" + e);
        }
    }

    /**
     * 关闭Ldap连接
     */
    public void close() {
        if (dc != null) {
            try {
                dc.close();
            } catch (NamingException e) {
                System.out.println("NamingException in close():" + e);
            }
        }
    }

    public void search(String name) {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//"none","simple","strong"
        env.put(Context.SECURITY_PRINCIPAL, adminName);
        env.put(Context.SECURITY_CREDENTIALS, adminPassword);
        env.put(Context.PROVIDER_URL, LDAP_URL);
        try {
            LdapContext ctx = new InitialLdapContext(env, null);
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String filterName = "*";
            if (name != null && !name.equals("")) {
                filterName = name;
            }
            String searchFilter = "(&(objectCategory=person)(objectClass=user)(mail=" + filterName + "))";
            String searchBase = "DC=test,DC=com";
            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchControls);
            while (answer.hasMoreElements()) {
                SearchResult sr = answer.next();
                System.out.println(sr);
                System.out.println("<<<::[" + sr.getName() + "]::>>>>");
            }
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
            System.err.println("Problem searching directory: " + e);
        }
    }

    /**
     * 主函数用于测试
     *
     * @param args
     */
    public static void main(String[] args) {
        LdapUtils ldap = new LdapUtils();
        ldap.init();
        ldap.search("test2@test.com");
        ldap.close();
    }
}
