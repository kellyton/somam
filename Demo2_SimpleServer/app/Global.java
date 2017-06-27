import play.*;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		Logger.info("############ Starting system...");
		Logger.info("#### Self registering...");
		Logger.info(controllers.Configuration.selfRegister().toString());
		Logger.info("#### Creating relationships...");
		Logger.info(controllers.Configuration.createBasicRelationships().toString());		
		Logger.info("############ System started! Waiting for calls.");
	}
}