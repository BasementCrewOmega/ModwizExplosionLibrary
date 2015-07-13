import org.gamejolt.DataStore.DataStoreType;
import org.gamejolt.GameJoltAPI;
import org.gamejolt.Trophy;


public class Testgame {
	public static void main(String[] args) {
		GameJoltAPI api = new GameJoltAPI(78651, "b837512411156582cef155b062537c87");
		api.setVerbose(true);
		api.verifyUser("modwizcode", "099b40");
		System.out.println(api.getDataStore(DataStoreType.GAME, "TestKey").getData());

		Trophy troph = api.getTrophy(34431);
		System.out.println(troph);
		api.sessionOpen();
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		api.sessionUpdate();
		api.sessionClose();
	}
}
