package prototype;

public abstract class Insecte {
	
	private int life;
	private int food;
	private int water;
	private int attack;
	
	public Insecte(int id){
		this.life=10;
		this.food=10;
		this.water=10;
		this.attack=2;
	}
	
	public abstract void eat();
	
	public abstract void drink();
	
	public abstract void death();
	
	public abstract void attack();
}
