package com.bolder.server.util;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.bolder.server.system.service.SpDataConfigurationService;

@Service
public class ScheduleTasksService {


	@Autowired
	private SpDataConfigurationService spDataConfigurationService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void initialize() {
		/*
		ColaEnvios colaNext = colaEnviosDao.findNext();
		if (colaNext==null)
			spDataConfigurationService.setQueueNextTime(null);
		else
			spDataConfigurationService.setQueueNextTime(colaNext.getSiguienteReintento());
		
		log.debug("Siguiente ejecucion cola ------>"+spDataConfigurationService.getQueueNextTime());
		*/
	}
	
	
	//CADA 6 HORAS
	@Scheduled(fixedDelay = 21600000)
	//@Scheduled(fixedDelay = 20000)
	private void DeleteRegisterTimeOut() {
		/*
		log.info("********INICIA SCHEDULED borra codigos de registro***");
		try {
			Calendar ahora = Calendar.getInstance();
			ahora.setTime(new Date());
			ahora.add(Calendar.HOUR, -6);
			Iterator<MobileRegister> it = mobileRegisterList.getRecords().iterator();
			while(it.hasNext()) {
				MobileRegister mobileRegister = it.next();
				log.info("********BORRA SCHEDULED "+mobileRegister.getLocalizador()+"***");
				if ( ahora.getTime().after(mobileRegister.getFechaCreacion()) ) {
					it.remove();
				}
			}				
		} catch (Exception e) {
			log.error("********ERROR SCHEDULED borra codigos de registro***");
			log.error(e.toString());
		}
		log.info("********FIN SCHEDULED borra codigos de registro***");
		*/
	}
	
	
	//CADA 8 HORAS
	@Scheduled(initialDelay=5000, fixedDelay = 60000)
	private void ProcesaColaEnvios() {
		
		/*
		
		try {
			if (spDataConfigurationService.getQueueNextTime()!=null && spDataConfigurationService.getQueueNextTime().before(new Date())) {
				
				log.info("********INICIA SCHEDULED procesa cola***");
				System.out.println("Procesa Cola");
				List<ColaEnvios> colaEnvios =colaEnviosDao.findAllToSend(new Date());
				
				for (ColaEnvios cola : colaEnvios) {
					if (cola.getCanalEnvio()==CanalEnvio.EMAIL) {
						//if (cola.getNaturalezaEnvio()==NaturalezaEnvio.CASO) {
							colaService.enviaMailCola(cola);
						//}
					}
				}
				
				ColaEnvios colaNext = colaEnviosDao.findNext();
				if (colaNext==null)
					spDataConfigurationService.setQueueNextTime(null);
				else {
					spDataConfigurationService.setQueueNextTime(colaNext.getSiguienteReintento());
					log.debug("Siguiente ejecucion cola ------>"+spDataConfigurationService.getQueueNextTime());
				}
				
				log.info("********FIN SCHEDULED procesa cola***");
			}			
		} catch (Exception e) {
			log.error("********ERROR SCHEDULED procesa cola***");
			log.error(e.toString());
		}
		
		*/

	}

}
