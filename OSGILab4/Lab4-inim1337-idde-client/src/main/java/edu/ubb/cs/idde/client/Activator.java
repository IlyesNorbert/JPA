/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.ubb.cs.idde.client;

import java.awt.EventQueue;
import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import edu.ubb.cs.idde.inter.UserItemDAO;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws InvalidSyntaxException {
		System.out.println("Starting the bundle Client");
		System.out.println("Starting the consumer bundle");
		Collection<ServiceReference<UserItemDAO>> references = context
				.getServiceReferences(UserItemDAO.class, "(service.name=Elso)");
		System.out.println(references + ":  references");
		if (!references.isEmpty()) {
			ServiceReference<UserItemDAO> reference = references.iterator().next();
			final UserItemDAO szolgaltatas = context.getService(reference);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ClientWindow frame = new ClientWindow(szolgaltatas);;
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		}else{
			System.out.println("ures references !!!!");
		}

	}
	public void stop(BundleContext context) {
		System.out.println("Stopping the bundle");
	}

}