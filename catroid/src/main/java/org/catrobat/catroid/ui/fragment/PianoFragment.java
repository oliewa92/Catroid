/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
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

package org.catrobat.catroid.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.bricks.Brick;
import org.catrobat.catroid.content.bricks.FormulaBrick;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.io.SoundManager;
import org.catrobat.catroid.pocketmusic.note.NoteName;
import org.catrobat.catroid.pocketmusic.ui.PianoView;

import java.util.Locale;

public class PianoFragment {

	private final FormulaBrick formulaBrick;
	private final Brick.BrickField noteField;

	private TextView noteValueView;

	public PianoFragment(FormulaBrick formulaBrick, Brick.BrickField noteField) {
		this.formulaBrick = formulaBrick;
		this.noteField = noteField;
	}

	public View getView(final Context context) {
		View selectorView = View.inflate(context, R.layout.note_selector_view, null);

		String currentFieldValue = getCurrentBrickFieldValue(context, noteField).trim();

		selectorView.setFocusableInTouchMode(true);
		selectorView.requestFocus();
		PianoView pianoView = selectorView.findViewById(R.id.piano_note_selector_view);
		pianoView.makeButtonsClickable(SoundManager.getInstance().getMidiNotePlayer(), this);
		pianoView.setButtonColor(NoteName.getNoteNameFromMidiValue(Integer.parseInt(currentFieldValue)), true);
		noteValueView = selectorView.findViewById(R.id.note_value);
		noteValueView.setText(currentFieldValue);
		noteValueView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FormulaEditorFragment.showFragment(context, formulaBrick, noteField);
			}
		});

		return selectorView;
	}

	public void updateFieldValue(int midiValue) {
		formulaBrick.setFormulaWithBrickField(noteField, new Formula(midiValue));
		noteValueView.setText(String.format(Locale.GERMAN, "%d", midiValue));
	}

	private String getCurrentBrickFieldValue(Context context, Brick.BrickField brickField) {
		return formulaBrick.getFormulaWithBrickField(brickField).getTrimmedFormulaString(context);
	}
}
