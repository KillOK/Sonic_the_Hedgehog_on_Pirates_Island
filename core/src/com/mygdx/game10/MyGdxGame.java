package com.mygdx.game10;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game10.GameScreen;

public class MyGdxGame extends Game {
	
	Animation walkAnimation;
	Animation walkAnimation1;
	Animation walkAnimation2;
	Animation walkAnimation3;
	Animation boringAnimationR;
	Animation boringAnimationL;
	Animation walkFastAnimationR;
	Animation walkFastAnimationL;
	Animation runFastAnimationR;
	Animation runFastAnimationL;
	Animation breakAnimationR;
	Animation breakAnimationL;
	Animation jumpAnimationR;
	Animation jumpAnimationL;
	
	Texture boringTextureR;
	Texture boringTextureL;
	Texture walkTexture;
	Texture stayRTexture;
	Texture stayLTexture;
	Texture walkTexture1;
	Texture walkFastTextureR;
	Texture walkFastTextureL;
	Texture breakTextureR;
	Texture breakTextureL;
	Texture runFastTextureR;
	Texture runFastTextureL;
	Texture jumpTextureR;
	Texture jumpTextureL;
	
	TextureRegion[] breakFramesR;
	TextureRegion[] breakFramesL;
	TextureRegion[] runFastFramesR;
	TextureRegion[] runFastFramesL;
	TextureRegion[] walkFastFramesR;
	TextureRegion[] walkFastFramesL;
	TextureRegion[] boringFramesR;
	TextureRegion[] boringFramesL;
	TextureRegion[] walkFrames;
	TextureRegion[] walkFrames1;
	TextureRegion[] walkFrames2;
	TextureRegion[] walkFrames3;
	TextureRegion[] jumpFramesR;
	TextureRegion[] jumpFramesL;
	
	TextureRegion currentBreakFramesR;
	TextureRegion currentBreakFramesL;
	TextureRegion currentRunFastFramesR;
	TextureRegion currentRunFastFramesL;
	TextureRegion currentWalkFastFramesR;
	TextureRegion currentWalkFastFramesL;
	TextureRegion currentFramesBoringR;
	TextureRegion currentFramesBoringL;
	TextureRegion currentFrames;
	TextureRegion currentFrames1;
	TextureRegion currentFrames2;
	TextureRegion currentFrames3;
	TextureRegion currentJumpFramesR;
	TextureRegion currentJumpFramesL;
	float time1;
	float time_2;
	
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch=new SpriteBatch();

		time1=0f;
		time_2=0f;
		try {
			runFastTextureR = new Texture("assets/Hero/RunFast1R.png");
			runFastTextureL = new Texture("assets/Hero/RunFast1L.png");
			breakTextureR = new Texture("assets/Hero/BreakR1.png");
			breakTextureL = new Texture("assets/Hero/BreakL1.png");
			walkTexture = new Texture("assets/Hero/Run_slow_1.png");
			walkTexture1 = new Texture("assets/Hero/Run_slow_L.png");
			walkFastTextureR = new Texture("assets/Hero/Run_slow_1.png");
			walkFastTextureL = new Texture("assets/Hero/Run_slow_L.png");
			stayRTexture = new Texture("assets/Hero/StayR.png");
			stayLTexture = new Texture("assets/Hero/StayL.png");
			boringTextureR = new Texture("assets/Hero/StayR.png");
			boringTextureL = new Texture("assets/Hero/StayL.png");
			jumpTextureR = new Texture("assets/Hero/Jump1f.png");
			jumpTextureL = new Texture("assets/Hero/Jump1L.png");
		}catch (Exception e){
			System.out.println(e);
		}

		try {
			runFastTextureR = new Texture("Hero/RunFast1R.png");
			runFastTextureL = new Texture("Hero/RunFast1L.png");
			breakTextureR = new Texture("Hero/BreakR1.png");
			breakTextureL = new Texture("Hero/BreakL1.png");
			walkTexture = new Texture("Hero/Run_slow_1.png");
			walkTexture1 = new Texture("Hero/Run_slow_L.png");
			walkFastTextureR = new Texture("Hero/Run_slow_1.png");
			walkFastTextureL = new Texture("Hero/Run_slow_L.png");
			stayRTexture = new Texture("Hero/StayR.png");
			stayLTexture = new Texture("Hero/StayL.png");
			boringTextureR = new Texture("Hero/StayR.png");
			boringTextureL = new Texture("Hero/StayL.png");
			jumpTextureR = new Texture("Hero/Jump1f.png");
			jumpTextureL = new Texture("Hero/Jump1L.png");
		}catch (Exception e){
			System.out.println(e);
		}
		TextureRegion[][] tmp = TextureRegion.split(walkTexture,100, walkTexture.getHeight());
		TextureRegion[][] tmp1 = TextureRegion.split(walkTexture1,100, walkTexture1.getHeight());
		TextureRegion[][] tmp2 = TextureRegion.split(stayRTexture,75, stayRTexture.getHeight());
		TextureRegion[][] tmp3 = TextureRegion.split(stayLTexture,100, stayLTexture.getHeight());
		TextureRegion[][] tmp4 = TextureRegion.split(boringTextureR,75, boringTextureR.getHeight());
		TextureRegion[][] tmp5 = TextureRegion.split(boringTextureL,75, boringTextureL.getHeight());
		TextureRegion[][] tmp6 = TextureRegion.split(walkFastTextureR,100, walkFastTextureR.getHeight());
		TextureRegion[][] tmp7 = TextureRegion.split(walkFastTextureL,100, walkFastTextureL.getHeight());
		TextureRegion[][] tmp8 = TextureRegion.split(runFastTextureR,73, runFastTextureR.getHeight());
		TextureRegion[][] tmp9 = TextureRegion.split(runFastTextureL,73, runFastTextureL.getHeight());
		TextureRegion[][] tmp10 = TextureRegion.split(breakTextureR,83, breakTextureR.getHeight());
		TextureRegion[][] tmp11 = TextureRegion.split(breakTextureL,83, breakTextureL.getHeight());
		TextureRegion[][] tmp12 = TextureRegion.split(jumpTextureR,70, jumpTextureR.getHeight());
		TextureRegion[][] tmp13 = TextureRegion.split(jumpTextureL,70, jumpTextureL.getHeight());
		
		boringFramesR=new TextureRegion[9];
		int index5=0;
		for(int k=0;k<9;k++){
				for(int z=0;z<1;z++){
					boringFramesR[index5++]=tmp4[z][k];}	
				}	
		
		boringFramesL=new TextureRegion[9];
		int index6=0;
		for(int k=0;k<9;k++){
				for(int z=0;z<1;z++){
					boringFramesL[index6++]=tmp5[z][k];}	
				}
		
		walkFrames2=new TextureRegion[1];
		int index2=0;
		for(int k=0;k<1;k++){
				for(int z=0;z<1;z++){
						walkFrames2[index2++]=tmp2[z][k];}	
				}	
		
		walkFrames3=new TextureRegion[1];
		int index3=0;
		for(int k=0;k<1;k++){
				for(int z=0;z<1;z++){
						walkFrames3[index3++]=tmp3[z][k];}	
				}	
		
		walkFrames1=new TextureRegion[8];
		int index1=0;
		for(int k=0;k<8;k++){
				for(int z=0;z<1;z++){
						walkFrames1[index1++]=tmp1[z][k];}	
				}	
		
		
		walkFrames=new TextureRegion[8];
		int index=0;
		for(int i=0;i<8;i++){
				for(int j=0;j<1;j++){
						walkFrames[index++]=tmp[j][i];
						}
				
			walkFastFramesR=new TextureRegion[8];
			int index7=0;
			for(int k=0;k<8;k++){
				for(int z=0;z<1;z++){
					walkFastFramesR[index7++]=tmp6[z][k];}	
			}	
				
				
			walkFastFramesL=new TextureRegion[8];
			int index8=0;
			for(int i1=0;i1<8;i1++){
				for(int j=0;j<1;j++){
					walkFastFramesL[index8++]=tmp7[j][i1];
				}
			}
			
			runFastFramesR=new TextureRegion[4];
			int index9=0;
			for(int k=0;k<4;k++){
					for(int z=0;z<1;z++){
						runFastFramesR[index9++]=tmp8[z][k];}	
					}	
			
			runFastFramesL=new TextureRegion[4];
			int index10=0;
			for(int k=0;k<4;k++){
					for(int z=0;z<1;z++){
						runFastFramesL[index10++]=tmp9[z][k];}	
					}	
			
			breakFramesR=new TextureRegion[4];
			int index11=0;
			for(int k=0;k<4;k++){
					for(int z=0;z<1;z++){
						breakFramesR[index11++]=tmp10[z][k];}	
					}	
			
			breakFramesL=new TextureRegion[4];
			int index12=0;
			for(int k=0;k<4;k++){
					for(int z=0;z<1;z++){
						breakFramesL[index12++]=tmp11[z][k];}	
					}	
			
			jumpFramesR=new TextureRegion[8];
			int index13=0;
			for(int k=0;k<8;k++){
					for(int z=0;z<1;z++){
						jumpFramesR[index13++]=tmp12[z][k];}	
					}	
			
			jumpFramesL=new TextureRegion[9];
			int index14=0;
			for(int k=0;k<8;k++){
					for(int z=0;z<1;z++){
						jumpFramesL[index14++]=tmp13[z][k];}	
					}
			
		} 
		
		breakAnimationR=new Animation(0.1f,breakFramesR);
		breakAnimationL=new Animation(0.1f,breakFramesL);
		runFastAnimationR=new Animation(0.1f,runFastFramesR);
		runFastAnimationL=new Animation(0.1f,runFastFramesL);
		walkAnimation=new Animation(0.1f,walkFrames);
		walkAnimation1=new Animation(0.1f,walkFrames1);
		walkFastAnimationR=new Animation(0.1f,walkFastFramesR);
		walkFastAnimationL=new Animation(0.08f,walkFastFramesL);
		walkAnimation2=new Animation(0.1f,walkFrames2);
		walkAnimation3=new Animation(0.1f,walkFrames2);
		boringAnimationR=new Animation(0.25f,boringFramesR);
		boringAnimationL=new Animation(0.25f,boringFramesL);
		jumpAnimationR=new Animation(0.1f,jumpFramesR);
		jumpAnimationL=new Animation(0.1f,jumpFramesL);

		this.setScreen(new GameScreen (this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
