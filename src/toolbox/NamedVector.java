/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox;

import java.util.Vector;

/**
 *
 * @author amb04
 */

class NamedVector extends Vector
{
  String name;

  public NamedVector(String name) {
    this.name = name;
  }

  public NamedVector(String name, Object elements[]) {
    this.name = name;
    for (int i = 0, n = elements.length; i < n; i++) {
      add(elements[i]);
    }
  }

        @Override
  public String toString() {
    return "[" + name + "]";
  }
}

