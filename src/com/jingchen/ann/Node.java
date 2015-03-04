package com.jingchen.ann;

public class Node
{
	public static final int TYPE_INPUT = 0;
	public static final int TYPE_HIDDEN = 1;
	public static final int TYPE_OUTPUT = 2;

	private int type;

	public void setType(int type)
	{
		this.type = type;
	}

	private float mForwardInputValue;
	private float mForwardOutputValue;

	private float mBackwardInputValue;
	private float mBackwardOutputValue;

	public Node()
	{
	}

	public Node(int type)
	{
		this.type = type;
	}

	/**
	 * sigmoid函数，这里用tanh-sigmoid，经测试其效果比log-sigmoid好！
	 * @param in
	 * @return
	 */
	private float forwardSigmoid(float in)
	{
		switch (type)
		{
		case TYPE_INPUT:
			return in;
		case TYPE_HIDDEN:
		case TYPE_OUTPUT:
			// return (float) (1 / (1 + Math.exp(-in)));
			return (float) ((Math.exp(in) - Math.exp(-in)) / (Math.exp(in) + Math
					.exp(-in)));
		}
		return 0;
	}

	private float backwardPropagate(float in)
	{
		switch (type)
		{
		case TYPE_INPUT:
			return in;
		case TYPE_HIDDEN:
		case TYPE_OUTPUT:
			// return mForwardOutputValue * (1 - mForwardOutputValue) * in;
			return (float) ((1 - Math.pow(mForwardOutputValue, 2)) * in);
		}
		return 0;
	}

	public float getForwardInputValue()
	{
		return mForwardInputValue;
	}

	public void setForwardInputValue(float mInputValue)
	{
		this.mForwardInputValue = mInputValue;
		setForwardOutputValue(mInputValue);
	}

	public float getBackwardInputValue()
	{
		return mBackwardInputValue;
	}

	public void setBackwardInputValue(float mBackwardInputValue)
	{
		this.mBackwardInputValue = mBackwardInputValue;
		setBackwardOutputValue(mBackwardInputValue);
	}

	public float getBackwardOutputValue()
	{
		return mBackwardOutputValue;
	}

	private void setBackwardOutputValue(float input)
	{
		this.mBackwardOutputValue = backwardPropagate(input);
	}

	public float getForwardOutputValue()
	{
		return mForwardOutputValue;
	}

	private void setForwardOutputValue(float mInputValue)
	{
		this.mForwardOutputValue = forwardSigmoid(mInputValue);
	}

}
