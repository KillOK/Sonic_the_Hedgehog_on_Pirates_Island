package com.mygdx.game10;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game10.GameScreen.Block;

class Persons {
	Rectangle rect;
	TextureRegion text;
	public float veloX=0,veloY=0;
	int activeX,activeY,onBlockX ,onBlockY, levelWidth, levelHeight;
	Block[][] level;
	boolean dirRight = false;
	boolean onGround = false;
	String f;
	
	
	//////////////Coordinates////////////
	
	float fps;
	
	float playerScreenX=0;
	float playerScreenY=0;
	
	float playerMapX=0;
	float playerMapY=0;
	
	
	float actionTime;
	
	////////////////////////////////////
	
	Persons(Rectangle rect,TextureRegion n,int levelWidth,int levelHeight, Block[][] level){
		this.level=level;
		this.levelWidth=levelWidth;
		this.levelHeight=levelHeight;
		this.rect=rect;
		this.text=n;
	}
	public void setVeloX(float f){
		this.veloX=f;
	}
	public float getVeloX(){
		return veloX;
	}
	public void setVeloY(float f){
		this.veloY=f;
	}
	public float getVeloY(){
		return veloY;
	}
	public Rectangle getRect(){
		return this.rect;
	}
	public TextureRegion getText(){
		return this.text;
	}
	
	public void defineLevelActivePart(){
		int x,y;
		for(x=0;x<levelWidth;x++){
			for(y=1;y<levelHeight;y++){
				if(((level[x][y].getRect().x<=getRect().x)&&(getRect().x<level[x][y].getRect().x+50))){
					activeX=x;
				}
				if(((level[x][y].getRect().y<=getRect().y)&&(getRect().y<level[x][y].getRect().y+50))){
					activeY=y;
				}
			}
		}
	}
	
	public boolean onGround(int activeX,int activeY){
		onBlockX=0;
		onBlockY=1;
		if(((activeX>=0)&&(activeX<=levelWidth))&&((activeY>=1)&&(activeY<=8))){
			if(level[activeX][activeY+1].getType()==1){
				onBlockX=activeX;
				onBlockY=activeY+1;
			}
			else if((dirRight==false)&&(activeX+1<levelWidth)&&(level[activeX+1][activeY+1].getType()==1)){
				onBlockX=activeX+1;
				onBlockY=activeY+1;
			}
			else if((dirRight==false)&&(activeX-1>=0)&&(level[activeX-1][activeY+1].getType()==1)){
				onBlockX=activeX-1;
				onBlockY=activeY+1;
			}
			else if((activeX-1<0)&&(level[0][activeY+1].getType()==1)){onGround=true;}
			else if((activeX-1<0)){onGround=false;}
			else if((dirRight==true)&&(activeX+1<=levelWidth-1)&&(level[activeX+1][activeY+1].getType()==1)){
				onBlockX=activeX+1;
				onBlockY=activeY+1;
			}
			else if((activeX+1>levelWidth-1)){onGround=false;}
		}
		if((getVeloY()<=0)&&(level[onBlockX][onBlockY].getRect().x-40<getRect().x)&&(getRect().x<level[onBlockX][onBlockY].getRect().x+50)&&(getRect().y<=level[onBlockX][onBlockY].getRect().y+52)&&(getRect().y>=level[onBlockX][onBlockY].getRect().y+45)){
			setVeloY(0);
			onGround=true;
		}else{
			onGround=false;
		}
		return onGround;
		
}
	
	public void MoveOnScreen(float x,float y){
		if((veloY<-1)&&(activeY<10)&&(activeY>=2)&&(Math.abs((level[activeX][activeY].getRect().y-this.rect.y))<=5)&&(level[onBlockX][onBlockY].type==1)&&(level[onBlockX][onBlockY].getRect().x-40<getRect().x)&&(getRect().x<level[onBlockX][onBlockY].getRect().x+50)){
			this.rect.x+=x;
			this.rect.y=level[onBlockX][activeY].getRect().y;
			onGround(activeX,activeY);
			
		}
		else{
			this.rect.x+=x;
			this.rect.y+=y;
		}
	}
}

