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

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BAM implements Serializable {



	static{

	}
	public static void main(String[] args) {




		GUI test = new GUI();

//		try {
//			FileOutputStream file = new FileOutputStream(fileName);
//			ObjectOutputStream out = new ObjectOutputStream(file);
//
//			// Method for serialization of object
//			out.writeObject(test);
//
//			out.close();
//			file.close();
//
//			System.out.println("Serialized GUI");
//		}catch(IOException ex){
//			System.out.println("IOException is caught");
//		}

		test.setVisible(true);

		test.setSize(new Dimension(700,700));
	}
}
