package cn.futuremove.adminportal.mongo.service;

import cn.futuremove.adminportal.mongo.dao.CarDao;
import cn.futuremove.adminportal.mongo.dao.DynamicMatDao;
import cn.futuremove.adminportal.mongo.dao.StaticMatDao;
import com.futuremove.cacheServer.entity.Car;
import com.futuremove.cacheServer.entity.DynamicMatCarInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *   NCar means new car, used the old for test
 * 
 * */
public class CarServiceImpl {
	
	final static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
	
	private CarDao carDao;
	private DynamicMatDao dynamicMatDao;
	private StaticMatDao staticMatDao;
	public CarDao getCarDao() {
		return carDao;
	}
	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
	}
	public DynamicMatDao getDynamicMatDao() {
		return dynamicMatDao;
	}
	public void setDynamicMatDao(DynamicMatDao dynamicMatDao) {
		this.dynamicMatDao = dynamicMatDao;
	}
	public StaticMatDao getStaticMatDao() {
		return staticMatDao;
	}
	public void setStaticMatDao(StaticMatDao staticMatDao) {
		this.staticMatDao = staticMatDao;
	}
	
	
	public CarServiceImpl(CarDao carDao, DynamicMatDao dynamicMatDao,
						  StaticMatDao staticMatDao) {
		super();
		this.carDao = carDao;
		this.dynamicMatDao = dynamicMatDao;
		this.staticMatDao = staticMatDao;
	}
	public CarServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	/************ business method  ******************/
	//@Override
	public void updateCarPosition(Car car) {

		logger.info("inside  updateCarPosition !!");
			try {
					//get the old data  car
					Car prevCar =  carDao.get(car.getVinNum());
					carDao.updateLocInfo(car);
					//update loc mat info
					if(prevCar==null) {
						//no such car
						logger.info("update status for a not found car");
						return ;
					} else if(prevCar.getState()== Car.state_busy) {
						logger.info("update a  status for a busy car");
						logger.info("first clear car info from static matrix");
						staticMatDao.removeCar(prevCar);
						logger.info("then update data into dynamic matrix");
						dynamicMatDao.updateCurrCarInfo(prevCar,car);
						return;
					} else if (prevCar.getState()==Car.state_free) {
						logger.info("update a  status for a free car");
						logger.info("first clear car info from dynamic matrix");
						dynamicMatDao.removeCar(prevCar);
						logger.info("then update data into static matrix");
						staticMatDao.updateCarInfo(prevCar,car);
						return;
					} else if(prevCar.getState() == Car.state_reserved || prevCar.getState()==Car.state_wait_code) {
						//other state
						dynamicMatDao.removeCar(prevCar);
						staticMatDao.removeCar(prevCar);
					}
			} catch(Exception e){
				logger.error(e.toString());
				return ;
			}

	}
	//@Override
	public void save(Car car) {
		// TODO Auto-generated method stub
		carDao.save(car);
		
	}
	
	public Car getByVinNum(String vinNum){
		return carDao.get(vinNum);
	}
	//@Override
	public void updateCarState(Car car) {
		logger.info("service: inside  updateCarPosition start !!");

	    carDao.updateCarState(car);
	    logger.info("service: inside  updateCarPosition over !!");

	}
	//@Override
	public void clearExpireReserve(Car car) {
		logger.info("inside  clearExpireReserve !!");

		carDao.clearExpireReserve(car);

		
	}
	
	public Car getByOwnerAndNotState(Car car) {
		// TODO Auto-generated method stub
		return carDao.getByOwnerAndNotState(car);
	}
	
	//set car state to pending, when car's state as free
	//@Override
	public void updateCarStateFree(Car car) {
		// TODO Auto-generated method stub
		logger.debug("carService  : update State to Pending start");

	    carDao.updateCarStateFree(car);

		logger.debug("carService : update State to Pending over ");	
	}
	
	
	
	//set car state to pending, when car's state as free
	//@Override
	public void updateCarStateWaitCode(Car car) {
		// TODO Auto-generated method stub
		logger.debug("carService  : update State to Pending start");

	    carDao.updateCarStateWaitCode(car);

		logger.debug("carService : update State to Pending over ");	
	}
	
	
	
	//set car state to pending, when car's state as free
	//@Override
	public void updateCarStateReservePending(Car car) {
		// TODO Auto-generated method stub
		logger.debug("carService  : update State to Pending start");

	    carDao.updateCarStateReservePending(car);

		logger.debug("carService : update State to Pending over ");	
	}
		
	//set car state to reserved, when car's state as pending
	//@Override
	public void updateCarStateReserved(Car car) {
		// TODO Auto-generated method stub
		logger.debug("carService : update State to reserved start");

		carDao.updateCarStateReserved(car);

		logger.debug("carService : update State to reserved over ");	
	}
		
	//set car state to reserved, when car's state as pending
	//@Override
	public void updateCarStateBusy(Car car) {
		// TODO Auto-generated method stub
		logger.debug("carService : update State to Busy start");

	     carDao.updateCarStateBusy(car);
		logger.debug("carService : update State to Busy over ");	
	}

	/**
	 * @should test
	 * @param likeCondition
	 * @return
	 */
	//@Override
	public List<Car> getFreeCarByScope(Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		//search in free matrix
		Long userX = StaticMatDao.getXIndex(Double.valueOf(likeCondition.get("userPositionX").toString()));
		Long userY = StaticMatDao.getYIndex(Double.valueOf(likeCondition.get("userPositionY").toString()));
		Long margin = Double.valueOf(likeCondition.get("scope").toString()).longValue();
		margin = margin/50;
		Long minX = userX - margin,maxX=userX+margin;
		Long minY = userY - margin,maxY=userY+margin;
		//first get the car vin num
		List<String> carVinNums = staticMatDao.getCarVinNumByScope(minX,maxX,minY,maxY);
		// then get the car
		List<Car> retCars = new ArrayList<Car>();
		for(String vinNum:carVinNums) {
			Car car = carDao.get(vinNum);
			if(car.getState()==Car.state_free)
				retCars.add(car);
		}
		return retCars;
	}
	//@Override
	public List<Car> getBusyCarByScope(Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		//search in free matrix
		Long userX = StaticMatDao.getXIndex(Double.valueOf(likeCondition.get("userPositionX").toString()));
		Long userY = StaticMatDao.getYIndex(Double.valueOf(likeCondition.get("userPositionY").toString()));
		Long margin = Double.valueOf(likeCondition.get("scope").toString()).longValue();
		margin = margin/50;
		Long minX = userX - margin,maxX=userX+margin;
		Long minY = userY - margin,maxY=userY+margin;
		//first get the car vin num
		List<DynamicMatCarInfo> infos = dynamicMatDao.getCarVinNumByScope(minX,maxX,minY,maxY);
		// then get the car
		List<Car> retCars = new ArrayList<Car>();
		for(DynamicMatCarInfo info:infos) {
			Car car = carDao.get(info.getVinNum());
			if(car.getState()==Car.state_busy)
				retCars.add(car);
		}
		return retCars;
	}
		
	

}
