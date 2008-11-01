/**
 * This is a java version of the game "Aquamatics" migrated from MacOS
 * Copyright (C) 2006  You XU 
 * Mathematics Department, Nanjing University
 * 03/30/2006
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *	
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * @version 0.0.1
 * This is the Controller.
 */

package org.orchesta.mathena.aquamatic.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.orchesta.mathena.aquamatic.Aquamatic;
import org.orchesta.mathena.aquamatic.model.GameMap;
import org.orchesta.mathena.aquamatic.model.ResourceManager;


public class AquaMouseListener implements MouseListener {
	private GameMap gm = ResourceManager.getInstance().getGameMap();
	private Aquamatic a;
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		
		if (gm.isSuccess()) return;

		Point p = arg0.getPoint();
		int y = p.x/30;
		int x = p.y/30;
		int state = gm.process(x,y);
		if (state==1) a.increaseStep(); 
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setAquamatic (Aquamatic a)
	{
		this.a = a;
	}

}

