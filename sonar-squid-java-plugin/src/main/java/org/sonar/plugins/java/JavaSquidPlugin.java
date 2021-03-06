/*
 * SonarQube Java
 * Copyright (C) 2012 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.java;

import com.google.common.collect.ImmutableList;
import org.sonar.api.CoreProperties;
import org.sonar.api.PropertyType;
import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;
import org.sonar.java.SonarComponents;

import java.util.List;

public class JavaSquidPlugin extends SonarPlugin {

  private static final String JAVA_CATEGORY = "java";
  private static final String GENERAL_SUBCATEGORY = "General";

  public static final String SQUID_ANALYSE_ACCESSORS_PROPERTY = "sonar.squid.analyse.property.accessors";
  public static final boolean SQUID_ANALYSE_ACCESSORS_DEFAULT_VALUE = true;

  @Override
  public List getExtensions() {
    return ImmutableList.of(
      PropertyDefinition.builder(JavaSquidPlugin.SQUID_ANALYSE_ACCESSORS_PROPERTY)
        .defaultValue(JavaSquidPlugin.SQUID_ANALYSE_ACCESSORS_DEFAULT_VALUE + "")
        .category(JAVA_CATEGORY)
        .subCategory(GENERAL_SUBCATEGORY)
        .name("Separate accessors")
        .description("Flag whether Squid should separate accessors (getters/setters) from methods. " +
          "In that case, accessors are not counted in metrics such as complexity or API documentation.")
        .type(PropertyType.BOOLEAN)
        .onQualifiers(Qualifiers.PROJECT)
        .build(),
      PropertyDefinition.builder(CoreProperties.DESIGN_SKIP_DESIGN_PROPERTY)
        .defaultValue(CoreProperties.DESIGN_SKIP_DESIGN_DEFAULT_VALUE + "")
        .category(JAVA_CATEGORY)
        .subCategory(GENERAL_SUBCATEGORY)
        .name("Skip design analysis")
        .type(PropertyType.BOOLEAN)
        .hidden()
        .build(),

      JavaRuleRepository.class,
      JavaSonarWayProfile.class,
      JavaSonarWayWithFindbugsProfile.class,
      SonarComponents.class,
      DefaultJavaResourceLocator.class,
      JavaSquidSensor.class);
  }

}
