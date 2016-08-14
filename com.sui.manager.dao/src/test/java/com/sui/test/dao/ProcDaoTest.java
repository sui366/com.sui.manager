//package com.sui.test.dao;
//
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.sui.manager.common.entity.po.ProcLsvs;
//import com.sui.manager.common.util.RandomUtil;
//import com.sui.manager.dao.ProcLsvsDao;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//// 读取spring配置文件
//public class ProcDaoTest{
//
//    @Autowired
//    private ProcLsvsDao procLsvsDao;
//    
//    private static int count = 1;
//
//    @Test
//    public void testquery() {
//    	System.out.println(procLsvsDao.get(1l));
//    }
//  
//    @Test 
//    public void testInsert() {
//    	
//    	for(int i=0; i<1000; i++){
//
//    		ProcLsvs obj = new ProcLsvs();
//    		obj.setId(uuid(10));
//    		obj.setProcAddr("192.168.1.11:808"+uuid(1));
//    		obj.setIsOnline(Integer.valueOf(String.valueOf(uuid(1))));
//    		obj.setLimitConnection(uuid(3));
//    		obj.setCurrConnection(uuid(2));
//    		obj.setCurrAuthenticated(uuid(1));
//    		obj.setCpuUsage(Integer.valueOf(String.valueOf(uuid(2))));
//    		obj.setMemUsage(Integer.valueOf(String.valueOf(uuid(2))));
//    		obj.setTotalBizSvrsCnt(Integer.valueOf(String.valueOf(uuid(2))));
//    		obj.setConnectedBizSvrsCnt(Integer.valueOf(String.valueOf(uuid(1))));
//    		obj.setForwardDownPktCnt(uuid(3));
//    		obj.setForwardFailedPktCnt(uuid(3));
//    		obj.setForwardUpPktCnt(uuid(3));
//    		
//    		obj.setStorageTime(addMin());
//    		
//    		procLsvsDao.save(obj);
//    		
////    		System.out.println("" + i);
////    		try {
////				Thread.sleep(100);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//    	}
//    	
//    }
//    
//    private Date addMin() {
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.setTime(new Date());
//		gc.add(GregorianCalendar.MINUTE, count++);
//
//		return gc.getTime();
//	}
//    
//    private Long uuid(int len){
//    	String uid =  "0";
//    	while (true) {
//    		uid = RandomUtil.getNumberCode(len);
//    		if(!uid.startsWith("0")){
//    			break;
//    		}
//		}
//    	return Long.valueOf(uid);
//    }
//    
//    
//   public static void main(String[] args) {
//	
//	   ProcDaoTest test = new ProcDaoTest();
//	   for(int i=0; i<2; i++){
//	   System.out.println(test.uuid(1));
//		   
//	   
////		   System.out.println(UUID.randomUUID());
//	   }
//   }
//}
