/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2017 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.pocketmusic.fastscroll;

import android.view.View;

public class Utils {

	private Utils() {

	}

	public static float getViewRawY(View view) {
		int[] location = new int[2];
		location[0] = 0;
		location[1] = (int) view.getY();
		((View) view.getParent()).getLocationInWindow(location);
		return location[1];
	}

	public static float getViewRawX(View view) {
		int[] location = new int[2];
		location[0] = (int) view.getX();
		location[1] = 0;
		((View) view.getParent()).getLocationInWindow(location);
		return location[0];
	}

	public static float getValueInRange(float min, float max, float value) {
		float minimum = Math.max(min, value);
		return Math.min(minimum, max);
	}
}