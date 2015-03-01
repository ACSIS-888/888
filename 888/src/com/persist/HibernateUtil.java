/**
 * 
 */
package com.persist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;

/**
 * @project : Hibernate-2-heritage
 * @package : com.tmax.persist
 * @file : HibernateUtil.java
 * @class : HibernateUtil
 * @author : Bruce GONG
 * @date : 2015年2月13日
 * @time : 上午10:20:49
 *
 */
@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	/**
	 * 打印：system.out.println();
	 * @param obj
	 */
	public static void showInConsole(Object obj) {
		String string=String.valueOf(obj);
		System.out.println(string);
	}
	
	/**
	 * 获取sessionFactory
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public static Session getSession() {
		Session s = (Session) session.get();
        // Ouvre une nouvelle Session, si ce Thread n'en a aucune
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
	}
	
	/**
	 * 释放session
	 * @param session
	 */
	public static void releaseSession() {
		//
		Session s = session.get();
    	session.set(null);
    	if(s!=null){
    		s.close();
    	}
		System.out.println("Close a seesion.");
	}
	
	/**
	 * 检查参数传值
	 * @param object
	 * @param isList
	 * @return
	 */
	public static boolean verifyObject(Object object, boolean isList) {
		if (isList) {//多元素列表检查
			List<Object> objects=(List<Object>)object;
			if (objects.isEmpty()) {//无元素检查
				return false;
			}
			for (Object obj : objects) {
				if (obj==null) {//元素为空检查
					return false;
				}
			}
		}else {//单元素检查
			if (object==null) {//元素为空检查
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 回滚和抛出异常
	 * @param tx
	 * @param e
	 */
	public static void rollAndThrow(Transaction tx, Exception e) {
		if (tx!=null) {
			tx.rollback();
		}
		throwException(e);
	}
	
	/**
	 * 抛出异常
	 * @param e
	 */
	public static void throwException(Exception e) {
		throw new HibernateException(e.getMessage());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
									/**
									 * CRUD操作方法
									 */
	
	/**
	 * 增--一个对象
	 * @param object
	 * @return 是否插入成功
	 */
	public static boolean add(Object object) {
		boolean result=false;
		if (verifyObject(object, false)) {
			Session session=null;
			Transaction tx=null;
			try {
				session=getSession();
				tx=session.beginTransaction();
				session.save(object);
				tx.commit();
				result=true;
			} catch (HibernateException e) {
				// TODO: handle exception
				rollAndThrow(tx, e);
			}finally{
				releaseSession();
			}
		}
		return result;
	}
	
	/**
	 * 增--多元素列表
	 * @param objects
	 * @return
	 */
	public static boolean add(List<Object> objects) {
		boolean result=false;
		if (verifyObject(objects, true)) {
			Session session=null;
			Transaction tx=null;
			try {
				session=getSession();
				tx=session.beginTransaction();
				for (Object object : objects) {
					session.save(object);
				}
				tx.commit();
				result=true;
			} catch (HibernateException e) {
				// TODO: handle exception
				rollAndThrow(tx, e);
			}finally{
				releaseSession();
			}
		}
		return result;
	}
	
	/**
	 * 改
	 * @param object
	 * @return 是否修改成功
	 */
	public static boolean update(Object object) {
		boolean result=false;
		if (verifyObject(object, false)) {
			Session session=null;
			Transaction tx=null;
			try {
				session=getSession();
				tx=session.beginTransaction();
				session.update(object);
				tx.commit();
				result=true;
			} catch (HibernateException e) {
				// TODO: handle exception
				rollAndThrow(tx, e);
			}finally{
				releaseSession();
			}
		}
		return result;
	}
	
	/**
	 * 删
	 * @param object
	 * @return 是否删除成功
	 */
	public static boolean delete(Object object) {
		boolean result=false;
		if (verifyObject(object, false)) {
			Session session=null;
			Transaction tx=null;
			try {
				session=getSession();
				tx=session.beginTransaction();
				session.delete(object);
				tx.commit();
				result=true;
			} catch (HibernateException e) {
				// TODO: handle exception
				rollAndThrow(tx, e);
			}finally{
				releaseSession();
			}
		}
		return result;
	}
	
	/**
	 * 查--根据主键查询
	 * @param clazz
	 * @param ID
	 * @return 获取的对象
	 */
	public static Object getByID(Class clazz, int ID) {
		Session session=null;
		Object object=null;
		try {
			session=getSession();
			object=session.get(clazz, ID);
		} catch (HibernateException e) {
			// TODO: handle exception
			throwException(e);
		}finally{
			releaseSession();
		}
		return object;
	}
	
	/**
	 * 查--根据非主键条件查询
	 * @param clazz
	 * @param criterion
	 * @return 获取的对象集合
	 */
	public static List<Object> getByCriteria(Class clazz, List<Criterion> criterions) {
		Session session=null;
		List<Object> objects=null;
		try {
			session=getSession();
			Criteria criteria=session.createCriteria(clazz);
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
			objects=criteria.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			throwException(e);
		}finally{
			releaseSession();
		}
		return objects;
	}
	
	/**
	 * 查--根据HQL（带参数）查询
	 * @param hql
	 * @param paramMap
	 * @return 获取的对象集合
	 */
	public static List<Object> getByHql(String hql,HashMap<String, String> paramMap) {
		Session session=null;
		List<Object> objects=null;
		try {
			session=getSession();
			Query query=session.createQuery(hql);
			if (!paramMap.isEmpty()) {
				for (Map.Entry<String, String> entry : paramMap.entrySet()) {
					query.setString(entry.getKey(), entry.getValue());					
				}
			}
			objects=query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			throwException(e);
		}finally{
			releaseSession();
		}
		return objects;
	}
	
	/**
	 * 查--根据HQL（无参数）查询
	 * @param hql
	 * @return 获取的对象集合
	 */
	public static List<Object> getByHql(String hql) {
		HashMap<String, String> paramMap=new HashMap<String, String>();
		return getByHql(hql, paramMap);
	}
}
