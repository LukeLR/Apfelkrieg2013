package apfelkrieg_danielOld;

public class ApfelkriegStart {
	public static void main(String[] args) {
		String[] path = new String[2];
		boolean[] output = new boolean[3];
		if(args.length != 6){
			//long start = System.currentTimeMillis();
			new ApfelkriegWindow();
			/*while(am.isYouDone() == false){}
			System.out.println("yeah");
			am.setVisible(false);
			path[0] = am.prefs()[4];
			path[1] = am.prefs()[5];
			output[0] = Boolean.parseBoolean(am.prefs()[1]);
			output[1] = Boolean.parseBoolean(am.prefs()[2]);
			output[2] = Boolean.parseBoolean(am.prefs()[3]);
			new Apfelkrieg(path, output).start(Integer.parseInt(am.prefs()[0]));*/
			
		} else {
			path[0] = args[4];
			path[1] = args[5];
			output[0] = Boolean.parseBoolean(args[1]);
			output[1] = Boolean.parseBoolean(args[2]);
			output[2] = Boolean.parseBoolean(args[3]);
			new Apfelkrieg(path, output, null).start(Integer.parseInt(args[0]));
		}
	}
}
