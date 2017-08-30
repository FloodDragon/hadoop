/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.yarn.server.nodemanager.containermanager.linux.runtime.docker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests the docker run command and its command
 * line arguments.
 */

public class TestDockerRunCommand {
  private DockerRunCommand dockerRunCommand;

  private static final String CONTAINER_NAME = "foo";
  private static final String USER_ID = "user_id";
  private static final String IMAGE_NAME = "image_name";

  @Before
  public void setUp() throws Exception {
    dockerRunCommand = new DockerRunCommand(CONTAINER_NAME, USER_ID,
        IMAGE_NAME);
  }

  @Test
  public void testGetCommandOption() {
    assertEquals("run", dockerRunCommand.getCommandOption());
  }

  @Test
  public void testCommandArguments() {
    String sourceDevice = "source";
    String destDevice = "dest";
    dockerRunCommand.addDevice(sourceDevice, destDevice);
    List<String> commands = new ArrayList<>();
    commands.add("launch_command");
    dockerRunCommand.setOverrideCommandWithArgs(commands);
    dockerRunCommand.removeContainerOnExit();
    assertEquals("run --name=foo --user=user_id --device=source:dest --rm "
            + "image_name launch_command",
        dockerRunCommand.getCommandWithArguments());
  }
}