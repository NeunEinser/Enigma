/*******************************************************************************
 * Copyright (c) 2015 Jeff Martin.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Contributors:
 * Jeff Martin - initial API and implementation
 ******************************************************************************/
package cuchaz.enigma.analysis;

import java.lang.reflect.Modifier;

import javassist.CtBehavior;
import javassist.CtField;

public enum Access {

    PUBLIC, PROTECTED, PACKAGE, PRIVATE;

    public static Access get(CtBehavior behavior) {
        return get(behavior.getModifiers());
    }

    public static Access get(CtField field) {
        return get(field.getModifiers());
    }

    public static Access get(int modifiers) {
        boolean isPublic = Modifier.isPublic(modifiers);
        boolean isProtected = Modifier.isProtected(modifiers);
        boolean isPrivate = Modifier.isPrivate(modifiers);

        if (isPublic && !isProtected && !isPrivate) {
            return PUBLIC;
        } else if (!isPublic && isProtected && !isPrivate) {
            return PROTECTED;
        } else if (!isPublic && !isProtected && isPrivate) {
            return PRIVATE;
        } else if (!isPublic && !isProtected && !isPrivate) {
            return PACKAGE;
        }
        // assume public by default
        return PUBLIC;
    }
}
