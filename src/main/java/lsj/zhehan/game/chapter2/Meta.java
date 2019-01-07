package lsj.zhehan.game.chapter2;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月21日 下午3:46:04
 * Modified By:
 */
@Setter
@Getter
public class Meta {
	
	private boolean up = false;
	
	private boolean down = false;
	
	private boolean left = false;
	
	private boolean right = false;
	
	private Integer X = 0;
	
	private Integer Y = 0;
	
	/**
	 * 
	 */
	public Meta() {
		super();
	}
	
	public Meta(Integer x, Integer y) {
		this.X = x;
		this.Y = y;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer desc = new StringBuffer();
		desc.append("XY=[" + X + "" + Y +"]");
		desc.append(" LEFT=" + left);
		desc.append(" UP=" + up);
		desc.append(" RIGHT=" + right);
		desc.append(" DOWN=" + down);
		String r = String.valueOf(desc);
		return r;
	}

	/**
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 * @param x
	 * @param y
	 */
	public Meta(boolean up, boolean down, boolean left, boolean right, Integer x, Integer y) {
		super();
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		X = x;
		Y = y;
	}
	
}
