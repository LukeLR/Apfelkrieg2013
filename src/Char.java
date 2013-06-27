public class Char {
	Garden ownGarden, enemyGarden;
	int appleCache, xPos, yPos, xNextApple = 0, yNextApple = 0, xRandomDirection, yRandomDirection;
	int ne, n, nw, w, sw, s, se, e;
	boolean appleDetected = false, intelligent;
	boolean[] d = new boolean[8];
	
	
	public Char(Garden own, Garden enemy, boolean ai){
		ownGarden = own;
		enemyGarden = enemy;
		intelligent = ai;
	}
	
	
	void walk(){
		//walks to next apple if present
		if(intelligent){
			if(d[0]){
				xPos--;
				yPos--;
			} else if(d[1]){
				yPos--;
			} else if(d[2]){
				xPos++;
				yPos--;
			} else if(d[3]){
				xPos++;
			} else if(d[4]){
				xPos++;
				yPos++;
			} else if(d[5]){
				yPos++;
			} else if(d[6]){
				xPos--;
				yPos++;
			} else if(d[7]){
				xPos--;
			}
		} else {
			if(appleDetected){
				xPos += xNextApple;
				yPos += yNextApple;
				ownGarden.removeApple(xPos, yPos);
				appleCache++;
				appleDetected = false;
			} else {
				xRandomDirection = 500;
				yRandomDirection = 500;
				while(	!(
							xPos + xRandomDirection >= 0 &&
							xPos + xRandomDirection < 10 &&
							yPos + yRandomDirection >= 0 &&
							yPos + yRandomDirection < 10
						)
					)
				{
					switch((int)(Math.random()*7)){
					case 0:
						xRandomDirection = -1;
						yRandomDirection = -1;
						break;
					case 1:
						xRandomDirection = -1;
						yRandomDirection = 0;
						break;
					case 2:
						xRandomDirection = -1;
						yRandomDirection = 1;
						break;
					case 3:
						xRandomDirection = 1;
						yRandomDirection = 0;
						break;
					case 4:
						xRandomDirection = 1;
						yRandomDirection = 1;
						break;
					case 5:
						xRandomDirection = 0;
						yRandomDirection = -1;
						break;
					case 6:
						xRandomDirection = -1;
						yRandomDirection = 1;
						break;
					case 7:
						xRandomDirection = -1;
						yRandomDirection = 0;
						break;
					}
				}
				xPos += xRandomDirection;
				yPos += yRandomDirection;
			}
		}
	}
	
	
	void throwApples(){
		enemyGarden.spreadApples(appleCache);
		appleCache = 0;
	}

	public void detectApple() {
		//for(int d = this.throwDice(); d > 0; d--){
		//while(xNextApple == 0 && yNextApple == 0 && appleDetected){
		appleDetected = false;
		if(ownGarden.isAppleOnField(xPos, yPos)){
			ownGarden.removeApple(xPos, yPos);
			appleCache++;
		}
		for(int x = -1; x <= 1; x++){
			for(int y = -1; y <= 1; y++){
				if(xPos + x >= 0 && xPos + x < 10 && yPos + y >= 0 && yPos + y < 10){
					if(ownGarden.isAppleOnField(xPos + x, yPos + y)){
						appleDetected = true;
						xNextApple=x;
						yNextApple=y;
					}
				}
			}
		}	
	}
	
	public void setDirection(int dice){
		// XXX get this to work
	}
}
