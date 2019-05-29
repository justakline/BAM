/*
 * Copyright (C) 2019 Best Advisory Maker, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javax.swing.*;
import java.awt.*;

public class BAM {
	public static void main(String[] args) {
		System.err.println("STart");
		JFrame f = new JFrame("Test");
		f.setVisible(true);
		f.setSize(new Dimension(500,500));
		WelcomeWindow w = new WelcomeWindow();
		f.add(w);
		JFrame f2 = new JFrame("Test2");
		f2.setVisible(true);
		f2.setSize(new Dimension(500,500));
		System.out.println("Gui");
		GUI test = new GUI();
		SettingsWindow s = new SettingsWindow(test);
		f2.add(s);
		System.out.println("F2");
		test.setVisible(true);
	}
}
