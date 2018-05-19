package com.mygdx.game10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game10.MyGdxGame;


	
		public class GameScreen   implements Screen   {
			final MyGdxGame game;
			OrthographicCamera camera;
			
			Texture backGround;
			Texture backCloud;
			Texture frontCloud;
			Texture blockTexture;

			Texture RightButton;
			Texture LeftButton;
			Texture JumpButton;
			Texture PickButton;
			
			float time;
			double time_1;
			float time3;
			float backgroundTime;
			
			Rectangle background1;
			Rectangle background2;
			Rectangle background3;
			Rectangle background4;
			Rectangle background5;
			Rectangle background6;

			boolean teleport=false;
			boolean pressLeft=false;
			boolean pressRight=false;
			public boolean pressJump=false;
			
			Persons Mario;
			
			Block[][] level;
			FileHandle levelFile;
			String levelString;
			String levelElementString;
			int levelWidth,levelHeight;
									
			public GameScreen(final MyGdxGame game){
				this.game=game;
				camera = new OrthographicCamera();
				camera.setToOrtho(false, 800, 480);
				
				Gdx.input.setInputProcessor((InputProcessor) new Input());

                TextureRegion n = null;

                try {
                    backCloud = new Texture("Clouds/Clouds back1.png");
                    frontCloud = new Texture("Clouds/Clouds front.png");
                    backGround = new Texture("Clouds/BackGround main.png");

                    RightButton = new Texture("Level/Right.png");
                    LeftButton = new Texture("Level/Left.png");
                    JumpButton = new Texture("Level/Jump.png");
                    PickButton = new Texture("Level/Pick-up.png");

                    background1 = new Rectangle(0, 0, 1117, (float) (570 * 0.8));
                    background2 = new Rectangle(1117, 0, 1117, (float) (570 * 0.8));
                    background3 = new Rectangle(0, 0, 1117, (float) (570 * 0.85));
                    background4 = new Rectangle(1117, 0, 1117, (float) (570 * 0.85));
                    background5 = new Rectangle(0, 0, 1210, 600);
                    background6 = new Rectangle(1210, 0, 1210, 600);

                    Texture walkTexture = new Texture("Game/StayR.png");

                    n = new TextureRegion(walkTexture, 73, walkTexture.getHeight());

                    blockTexture = new Texture("Game/Block.png");

                    levelFile = Gdx.files.internal("Level/Level1.lvl");

                    levelString = levelFile.readString();
                }catch (Exception e){
                    e.printStackTrace();
                }
				
				levelHeight=Integer.parseInt(getNextString());
				levelWidth= new Integer((getNextString().length()/2)-2);

				level=new Block[levelWidth][levelHeight];
				
				int x,y;
				for(y=1;y<levelHeight;y++){
					levelElementString=getNextString();
					for(x=0;x<levelWidth;x++){
						level[x][y]=new Block(new Rectangle(50*x,(50f*(levelHeight-y)+50),50,50),getNextElement(levelElementString));
					}}
				
				Mario=new Persons(new Rectangle(400,100,73,150),n, levelWidth,levelHeight, level);
				Mario.dirRight=true;
				
				parseStart();
			}
			
			private void parseStart(){
				int x,y,x_c=0,y_c=0;
				for(x=0;x<levelWidth;x++){
					for(y=1;y<levelHeight;y++){
						if(level[x][y].getType()==3){
								x_c=x;
								y_c=y;
								break;
						}
							
					}
				}
				for(x=0;x<levelWidth;x++){
					for(y=1;y<levelHeight;y++){
						level[x][y].Move(200,-200);
					}
				}
				Mario.rect.x=level[x_c][y_c].getRect().x;
				Mario.rect.y=level[x_c][y_c].getRect().y+1;
				
			}
			
			private void teleportToStart(){
				
				//Mario.rect.x=level[Mario.activeX][3].getRect().x; //���� ������� �������� ��������� �� ���� ��������� �����
				Mario.rect.y=level[3][3].getRect().y;
				teleport=false;
			}

			@Override
			public void show() {
				// TODO Auto-generated method stub
				
			}

			private String getNextString(){
				String outString;
				String time;
				int j,i=0;
				outString="";
				while(levelString.charAt(i)!='\n'){
					outString+=levelString.charAt(i);
					i++;
				}
				time="";
				for(j=i+1;j<levelString.length();j++){
					time+=levelString.charAt(j);
				}
				levelString=time;
				return outString;
			}
			
			private int getNextElement(String string){
				int i,j,type;
				type=0;
				i=0;
				String time="";
				while(string.charAt(i)!=' ') {
					time+=string.charAt(i);
					i++;
				}
				type=Integer.parseInt(time);
				time="";
				for(j=i+1;j<string.length();j++){
					time+=string.charAt(j);
				}
				levelElementString=time;
				return type;
			}
			
			@Override
			public void render(float delta) {
				int x,y;
				update();
				
				
				Gdx.gl.glClearColor(0, 0, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
				time_1=0f;
				time_1+=Gdx.graphics.getDeltaTime();
				
				//////////////////////////////////////////////
				Mario.actionTime+=Gdx.graphics.getDeltaTime();
				game.time1+=Gdx.graphics.getDeltaTime();
				game.time_2+=Gdx.graphics.getDeltaTime();
				
				if(game.time1>0.1*8){
					game.time1=0;
				}
				if(game.time_2>0.25*9.5){
					game.time_2=0;
				}
				/////////////////////////////////////////////////////////
				
				camera.update();
				game.batch.setProjectionMatrix(camera.combined);
				
				game.currentFrames3=(TextureRegion) game.walkAnimation3.getKeyFrame(game.time1);
				game.currentFrames2=(TextureRegion) game.walkAnimation2.getKeyFrame(game.time1);
				game.currentFrames1=(TextureRegion) game.walkAnimation1.getKeyFrame(game.time1);
				game.currentFrames=(TextureRegion) game.walkAnimation.getKeyFrame(game.time1);
				game.currentFramesBoringR=(TextureRegion) game.boringAnimationR.getKeyFrame(game.time_2);
				game.currentFramesBoringL=(TextureRegion) game.boringAnimationL.getKeyFrame(game.time_2);
				game.currentWalkFastFramesR=(TextureRegion) game.walkFastAnimationR.getKeyFrame(game.time1);
				game.currentWalkFastFramesL=(TextureRegion) game.walkFastAnimationL.getKeyFrame(game.time1);
				game.currentRunFastFramesR=(TextureRegion) game.runFastAnimationR.getKeyFrame(game.time1);
				game.currentRunFastFramesL=(TextureRegion) game.runFastAnimationL.getKeyFrame(game.time1);
				game.currentBreakFramesR=(TextureRegion) game.breakAnimationR.getKeyFrame(game.time1);
				game.currentBreakFramesL=(TextureRegion) game.breakAnimationL.getKeyFrame(game.time1);
				game.currentJumpFramesR=(TextureRegion) game.jumpAnimationR.getKeyFrame(game.time1);
				game.currentJumpFramesL=(TextureRegion) game.jumpAnimationL.getKeyFrame(game.time1);
				
					
				game.batch.begin();
				
				game.batch.setProjectionMatrix(camera.combined);
				
				game.batch.draw(backGround, background5.x,background5.y,background5.width,background5.height);
				game.batch.draw(backGround, background6.x,background6.y,background6.width,background6.height);
				game.batch.draw(backCloud, background1.x,background1.y,background1.width,background1.height);
				game.batch.draw(backCloud, background2.x,background2.y,background2.width,background2.height);
				game.batch.draw(frontCloud, background3.x,background3.y,background3.width,background3.height);
				game.batch.draw(frontCloud, background4.x,background4.y,background4.width,background4.height);
				
				for(y=1;y<levelHeight;y++){
					for(x=0;x<levelWidth;x++){
						if(level[x][y].getType()==1){
							game.batch.draw(blockTexture,level[x][y].getRect().x,level[x][y].getRect().y,level[x][y].getRect().width,level[x][y].getRect().height); 
						}
					}
				}
				
				if (pressRight==true&&Mario.veloX>=0&&(pressJump==false)){
					if(Mario.actionTime<=1&&Mario.veloX<1){Mario.veloX+=1;}
					else if((Mario.actionTime>1)&&(Mario.veloX<6)&&(pressJump==false)){Mario.veloX+=0.05;}
					if(Mario.veloX<4){
					if(Mario.veloX>=2.2){Mario.fps=(float) 0.09;
					if(game.time1>Mario.fps*8){game.time1=0;}}
					else if(Mario.veloX>=2.2&&(pressJump==false)){Mario.fps=(float) 0.1;
					if(game.time1>Mario.fps*8){game.time1=0;}}
					game.batch.draw(game.currentFrames, Mario.getRect().x-25, Mario.getRect().y-35);}
					if(Mario.veloX>=4){if(game.time1>0.1*4){game.time1=0;}
					game.batch.draw(game.currentRunFastFramesR,Mario.getRect().x-25, Mario.getRect().y-25);}
					}

				else if (pressLeft==true&&Mario.veloX<=0&&(pressJump==false)){ 
					if(Mario.actionTime<=1&&Mario.veloX>(-1)){Mario.veloX=(Mario.veloX-1);}
					else if((Mario.actionTime>1)&&(Mario.veloX>-6)){Mario.veloX-=0.05;}
					if(Mario.veloX>(-2.2)){game.batch.draw(game.currentFrames1,Mario.getRect().x-25, Mario.getRect().y-35);}
					else if(Mario.veloX<=(-2.2)&&Mario.veloX>(-4)){if(game.time1>0.08*8){
						game.time1=0;}
							game.batch.draw(game.currentWalkFastFramesL,Mario.getRect().x-25, Mario.getRect().y-35);}
					else if(Mario.veloX<=(-4)&&(pressJump==false)){
						if(game.time1>0.1*4){game.time1=0;}
						game.batch.draw(game.currentRunFastFramesL,Mario.getRect().x-25, Mario.getRect().y-25);}}
				
				////////////////turn back with high speed/////////////////must add dust.../// �������� ����������� ��� ��������� ��������
				
				
				else if (pressRight==true&&Mario.veloX<0&&(pressJump==false)){
					if (Mario.veloX<=(-2)){
						Mario.veloX+=0.05;
					if(game.time1>0.1*4){game.time1=0;}
					game.batch.draw(game.currentBreakFramesL,Mario.getRect().x-25, Mario.getRect().y-50);}
					else if (Mario.veloX>(-2)){
						Mario.veloX=0;
					}
				}
				
				else if (pressLeft==true&&Mario.veloX>0&&(pressJump==false)){
					if (Mario.veloX>=2){
						Mario.veloX-=0.05;
					if(game.time1>0.1*4){game.time1=0;}
					game.batch.draw(game.currentBreakFramesR,Mario.getRect().x-25, Mario.getRect().y-50);}
					else if (Mario.veloX<2){
						Mario.veloX=0;}
				}
					
				///////////////speed down and stay///////////////////////

				else if((pressLeft==false)&&(Mario.dirRight==false)&&(pressJump==false)) {
					Mario.actionTime=0;
					if(Mario.veloX<0){Mario.veloX+=0.05;
					game.batch.draw(game.currentFrames1,Mario.getRect().x-25, Mario.getRect().y-35);
					}
					else{game.walkAnimation3=new Animation(0.1f,game.walkFrames3);
					Mario.veloX=0;
					game.batch.draw(game.currentFrames3,Mario.getRect().x-25, Mario.getRect().y-35);}}
				
				else if((pressRight==false)&&(Mario.dirRight==true)&&(pressJump==false)) {
					Mario.actionTime=0;
					if(Mario.veloX>0){Mario.veloX-=0.05;
					game.batch.draw(game.currentFrames,Mario.getRect().x-25, Mario.getRect().y-35);}	
					else{game.walkAnimation2=new Animation(0.1f,game.walkFrames2);
					Mario.veloX=0;
					game.batch.draw(game.currentFrames2,Mario.getRect().x-25, Mario.getRect().y-35);}}
				
				///////////////////////////////////////Jump//////////////////////////////////////////////
				
				else if((pressJump==true)&&(Mario.dirRight==false)){
					game.batch.draw(game.currentJumpFramesL,Mario.getRect().x-25, Mario.getRect().y-15);
					if (Mario.onGround&&Mario.getVeloY()==0){
						pressJump=false;
						}
					}
				
				else if((pressJump==true)&&(Mario.dirRight==true)){
					game.batch.draw(game.currentJumpFramesR,Mario.getRect().x-25, Mario.getRect().y-15);
					if (Mario.onGround&&Mario.getVeloY()==0){
						pressJump=false;
						}
					}
				
				
			
				//game.batch.draw(Mario.getText(), Mario.getRect().x-25, Mario.getRect().y-35,Mario.getRect().width,Mario.getRect().height);

				game.batch.draw(LeftButton, 0,420-LeftButton.getHeight()+65,LeftButton.getWidth(),LeftButton.getHeight());
				game.batch.draw(RightButton, 133,420-RightButton.getHeight()+65,RightButton.getWidth(),RightButton.getHeight());
				game.batch.draw(JumpButton, 266+75,420-JumpButton.getHeight()+75,JumpButton.getWidth(),JumpButton.getHeight());
				game.batch.draw(PickButton, 800-PickButton.getWidth()+50,420-PickButton.getHeight()+70,PickButton.getWidth()-50,PickButton.getHeight());

				game.batch.end();
				
			}
			
			private void update(){
				
				time=2f;
				time+=Gdx.graphics.getDeltaTime();
				backgroundTime=2f;
				backgroundTime+=Gdx.graphics.getDeltaTime();
				int x,y;
				Mario.defineLevelActivePart();
				Mario.onGround(Mario.activeX,Mario.activeY);
				System.out.println(Mario.activeX+" "+Mario.onBlockX);
				if((pressJump)&&(Mario.onGround)){
					Mario.setVeloY(10);
					Mario.onGround=false;
					Mario.MoveOnScreen(0f, Mario.getVeloY());
				}
				
				if((pressJump==false)&&(Mario.onGround==true)){
					Mario.setVeloY(0f);
					Mario.MoveOnScreen(0f, Mario.getVeloY());
				}
				else if(((pressJump==false)&&(Mario.onGround==false))||((pressJump==true)&&(Mario.onGround==false))){
					if(Mario.getVeloY()>=-5){Mario.setVeloY(Mario.getVeloY()-0.5f);}
					Mario.MoveOnScreen(0f, Mario.getVeloY());
					
				}
				
				
					for(y=1;y<levelHeight;y++){
						for(x=0;x<levelWidth;x++){
							level[x][y].Move(-(float) (Mario.getVeloX()*time), 0);
						}}	
					
				
				/*if(pressLeft){
					if(Mario.getRect().x-73<400){
						
						Mario.MoveOnScreen(0.5f, 0f);
						Mario.setVeloX(2);
						
						background5.x+=(Mario.getVeloX()-0.5)*time;
						background6.x+=(Mario.getVeloX()-0.5)*time;
						background3.x+=(Mario.getVeloX()-0.5)*time;
						background4.x+=(Mario.getVeloX()-0.5)*time;
						background1.x+=(Mario.getVeloX()-0.5)*time;
						background2.x+=(Mario.getVeloX()-0.5)*time;
						for(y=1;y<levelHeight;y++){
							for(x=0;x<levelWidth;x++){
								level[x][y].Move((float) ((Mario.getVeloX()-0.5)*time), 0);
							}}
					}else {
					background5.x+=Mario.getVeloX()*time;
					background6.x+=Mario.getVeloX()*time;
					background3.x+=Mario.getVeloX()*time;
					background4.x+=Mario.getVeloX()*time;
					background1.x+=Mario.getVeloX()*time;
					background2.x+=Mario.getVeloX()*time;
					for(x=0;x<levelWidth;x++){
						for(y=1;y<levelHeight;y++){
							level[x][y].Move((float) ((Mario.getVeloX())*time), 0);
						}}
					
				}}
				
				if(pressRight){
					if(Mario.getRect().x-73>250){
						
						Mario.setVeloX(-2f);
						Mario.MoveOnScreen(-0.5f, 0f);
						
						background5.x+=(Mario.getVeloX()+0.5)*time;
						background6.x+=(Mario.getVeloX()+0.5)*time;
						background3.x+=(Mario.getVeloX()+0.5)*time;
						background4.x+=(Mario.getVeloX()+0.5)*time;
						background1.x+=(Mario.getVeloX()+0.5)*time;
						background2.x+=(Mario.getVeloX()+0.5)*time;
						for(y=1;y<levelHeight;y++){
							for(x=0;x<levelWidth;x++){
								level[x][y].Move((float) ((Mario.getVeloX()+0.5)*time), 0);
							}}
					}else {
					background5.x+=Mario.getVeloX()*time;
					background6.x+=Mario.getVeloX()*time;
					background3.x+=Mario.getVeloX()*time;
					background4.x+=Mario.getVeloX()*time;
					background1.x+=Mario.getVeloX()*time;
					background2.x+=Mario.getVeloX()*time;
					for(x=0;x<levelWidth;x++){
						for(y=1;y<levelHeight;y++){
							level[x][y].Move((float) ((Mario.getVeloX())*time), 0);
						}}
					
				}}*/
				
				background3.x+=0.5*backgroundTime;
				background4.x+=0.5*backgroundTime;
				background1.x+=0.25*backgroundTime;
				background2.x+=0.25*backgroundTime;
				
	///////////////////////////////////////////////////////
				
				
		///////////Background movement in action//////////
				
				background5.x-=Mario.veloX*backgroundTime;
				background6.x-=Mario.veloX*backgroundTime;
				background3.x-=Mario.veloX*backgroundTime;
				background4.x-=Mario.veloX*backgroundTime;
				background1.x-=Mario.veloX*backgroundTime;
				background2.x-=Mario.veloX*backgroundTime;
				
			if(background5.x<-1210){
				background5.x=background6.x+1210;
			}
			if(background6.x<-1210){
				background6.x=background5.x+1210;
			}
			
			if(background5.x>0){
				background6.x=background5.x-1210;
			}
			if(background5.x>800)
			{
				background5.x=background6.x-1210;
			}
			if(background6.x>0){
				background5.x=background6.x-1210;
			}
			if(background6.x>800)
			{
				background6.x=background5.x-1210;
			
			}
			
			if(background1.x<-1117){
				background1.x=background2.x+1117;
			}
			if(background2.x<-1117){
				background2.x=background1.x+1117;
			}
			
			if(background1.x>0){
				background2.x=background1.x-1117;
			}
			if(background1.x>800)
			{
				background1.x=background2.x-1117;
			}
			if(background2.x>0){
				background1.x=background2.x-1117;
			}
			if(background2.x>800)
			{
				background2.x=background1.x-1117;
			}
			
			if(background3.x<-1117){
				background3.x=background4.x+1117;
			}
			if(background4.x<-1117){
				background4.x=background3.x+1117;
			}
			
			if(background3.x>0){
				background4.x=background3.x-1117;
			}
			if(background3.x>800)
			{
				background3.x=background4.x-1117;
			}
			if(background4.x>0){
				background3.x=background4.x-1117;
			}
			if(background4.x>800)
			{
				background4.x=background3.x-1117;
			}}
				/*if(background5.x<-1210){
					background5.x=background6.x+1210;
				}
				if(background6.x<-1210){
					background6.x=background5.x+1210;
				}
				
				if(background5.x>0){
					background6.x=background5.x-1210;
				}
				if(background5.x>800)
				{
					background5.x=background6.x-1210;
				}
				if(background6.x>0){
					background5.x=background6.x-1210;
				}
				if(background6.x>800)
				{
					background6.x=background5.x-1210;
				
				}
				
				if(background1.x<-1117){
					background1.x=background2.x+1117;
				}
				if(background2.x<-1117){
					background2.x=background1.x+1117;
				}
				
				if(background1.x>0){
					background2.x=background1.x-1117;
				}
				if(background1.x>800)
				{
					background1.x=background2.x-1117;
				}
				if(background2.x>0){
					background1.x=background2.x-1117;
				}
				if(background2.x>800)
				{
					background2.x=background1.x-1117;
				}
				
				if(background3.x<-1117){
					background3.x=background4.x+1117;
				}
				if(background4.x<-1117){
					background4.x=background3.x+1117;
				}
				
				if(background3.x>0){
					background4.x=background3.x-1117;
				}
				if(background3.x>800)
				{
					background3.x=background4.x-1117;
				}
				if(background4.x>0){
					background3.x=background4.x-1117;
				}
				if(background4.x>800)
				{
					background4.x=background3.x-1117;
				}	
				
				Mario.onGround=Mario.onGround(Mario.activeX,Mario.activeY);
				
			}*/

			@Override
			public void resize(int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void pause() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void resume() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void hide() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
			
			class Input implements InputProcessor{

				@Override
				public boolean keyDown(int keycode) {
					if(keycode==Keys.LEFT){
						pressLeft=true;
						Mario.dirRight=false;
						pressRight=false;
						Mario.actionTime=0;
					}
					if(keycode==Keys.RIGHT){
						pressRight=true;
						pressLeft=false;
						Mario.dirRight=true;
						Mario.actionTime=0;
					}
					
					if(keycode==Keys.UP){
						if(Mario.onGround){
							pressJump=true;
							Mario.actionTime=0;
						}
					}
					
					if(keycode==Keys.DOWN){
						teleportToStart();
					}
					
					return false;
				}

				@Override
				public boolean keyUp(int keycode) {
					if(keycode==Keys.LEFT){
						pressLeft=false;
						Mario.actionTime=0;
					}
					if(keycode==Keys.RIGHT){
						pressRight=false;
						Mario.actionTime=0;
					}
					if(keycode==Keys.UP){
						pressJump=false;
						if(Mario.getVeloY()>=3){Mario.setVeloY(3);}
					}
					return false;
				}

				@Override
				public boolean keyTyped(char character) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean touchDown(int screenX, int screenY, int pointer, int button) {
					// TODO Auto-generated method stub

					if(screenX>800-133&&screenX<=800&&screenY<80){
						teleportToStart();
						teleport=true;
					}

					if(screenX>266&&screenX<=800&&teleport==false){
						if(Mario.onGround){
							pressJump=true;
							Mario.actionTime=0;
						}
					}
					
					if(screenX>=0&&screenX<=133){
						pressLeft=true;
						Mario.dirRight=false;
						pressRight=false;
						Mario.actionTime=0;
					}
					else if(screenX>133&&screenX<=266){
						pressRight=true;
						pressLeft=false;
						Mario.dirRight=true;
						Mario.actionTime=0;
					}
					
					
					return false;
				}

				@Override
				public boolean touchUp(int screenX, int screenY, int pointer, int button) {
					// TODO Auto-generated method stub

					if(screenX>800-133&&screenX<=800&&screenY<76){
						teleport=false;
					}

					if(screenX>266&&screenX<=800){
						pressJump=false;
						if(Mario.getVeloY()>=3){Mario.setVeloY(3);}
					}
					
					if(screenX>=0&&screenX<=133){
						pressLeft=false;
						Mario.actionTime=0;
					}
					
					if(screenX>133&&screenX<=266){
						pressRight=false;
						Mario.actionTime=0;
					}
					return false;
				}

				@Override
				public boolean touchDragged(int screenX, int screenY, int pointer) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean mouseMoved(int screenX, int screenY) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean scrolled(int amount) {
					// TODO Auto-generated method stub
					return false;
				}
			}
			
			class Block{
				Rectangle rect;
				int type;
				Block(Rectangle rect,int type){
					this.rect=rect;
					this.type=type;
				}
				public Rectangle getRect(){
					return rect;
				}
				public int getType(){
					return this.type;
				}
				public void Move(float x,float f){
					this.rect.x+=x;
					this.rect.y+=f;
				}
			}
		}
			

			
