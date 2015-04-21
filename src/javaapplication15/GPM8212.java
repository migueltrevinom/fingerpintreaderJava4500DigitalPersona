/*
 * Copyright 2010 - Brian Oppenheim (brianopp.com)
 *
 * This file is part of java-gpm8212.
 *
 * java-gpm8212 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java-gpm8212 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with java-gpm8212.  If not, see <http://www.gnu.org/licenses/>.
 */

package javaapplication15;

import java.util.Date;

import app.Com;
import app.Parameters;

/**
 * A class for communicating with a GWInstek GPM-8212.
 *
 * @author Brian Oppenheim
 */
// TODO(brianopp): Document the string return values.
public class GPM8212 {

  /** Measurement reading modes. */
  public enum MeasurementStatus {MAXIMUM, MINIMUM, NORMAL}

  /** Volt range settings. */
  public enum VoltRange {
    /** 640V */
    V640,
    /** 320V */
    V320,
    /** 160V */
    V160,
    /** 80V */
    V80,
    /** 40V */
    V40,
    /** 20V */
    V20,
    /** 10V */
    V10,
    /** 5V */
    V5,
    /** Autorange */
    VAUTO}

  /** Amp range settings. */
  public enum AmpRange {
    /** 20.48A */
    A20_48,
    /** 10.24A */
    A10_24,
    /** 5.12A */
    A5_12,
    /** 2.56A */
    A2_56,
    /** 1.28A */
    A1_28,
    /** 0.64A */
    A_64,
    /** 0.32A */
    A_32,
    /** 0.16A */
    A_16,
    /** Autorange */
    AAUTO}

  /** the character that terminates outgoing and incoming messages */
  private static final char STOP_CHARACTER = 0x0D;

  /** communications port for the serial interface */
  private Com port;

  /**
   * Constructs a new {@link GPM8212} to communicate on the given {@link Com} port.
   *
   * @param port the {@link Com} object to communicate on the serial port
   */
  public GPM8212(Com port) {
    this.port = port;
  }

  /**
   * Sets whether or not the data hold option is enabled.
   *
   * @param enable should the data hold option be enabled
   * @throws Exception on any exception sending the command
   */
  public void setDataHoldEnabled(boolean enable) throws Exception {
    if (enable) {
      this.sendCommand("F00");
    } else {
      this.sendCommand("F01");
    }
  }

  /**
   * Sets the measurement status option. 
   *
   * @param measurementStatus the type of measurement to be taking
   * @throws Exception on any exception sending the command
   */
  public void setMeasurementStatus(MeasurementStatus measurementStatus) throws Exception {
    switch (measurementStatus) {
      case MAXIMUM:
        this.sendCommand("F02");
        break;
      case MINIMUM:
        this.sendCommand("F03");
        break;
      case NORMAL:
        this.sendCommand("F04");
        break;
      default:
        throw new UnsupportedOperationException("Not sure what to do with measurement status " +
                                                measurementStatus + ".");
    }
  }

  /**
   * Sets the volt range on the meter.
   *
   * @param voltRange the volt range setting to set
   * @throws Exception on any exception sending the command
   */
  public void setVoltRange(VoltRange voltRange) throws Exception {
    switch (voltRange) {
      case V640:
        this.sendCommand("R00");
        break;
      case V320:
        this.sendCommand("R01");
        break;
      case V160:
        this.sendCommand("R02");
        break;
      case V80:
        this.sendCommand("R03");
        break;
      case V40:
        this.sendCommand("R04");
        break;
      case V20:
        this.sendCommand("R05");
        break;
      case V10:
        this.sendCommand("R06");
        break;
      case V5:
        this.sendCommand("R07");
        break;
      case VAUTO:
        this.sendCommand("R16");
        break;
      default:
        throw new UnsupportedOperationException("Not sure what to do with volt range " +
                                                voltRange + ".");
    }
  }

  /**
   * Sets the amp range on the meter.
   *
   * @param ampRange the amp range setting to set
   * @throws Exception on any exception sending the command
   */
  public void setAmpRange(AmpRange ampRange) throws Exception {
    switch (ampRange) {
      case A20_48:
        this.sendCommand("R08");
        break;
      case A10_24:
        this.sendCommand("R09");
        break;
      case A5_12:
        this.sendCommand("R10");
        break;
      case A2_56:
        this.sendCommand("R11");
        break;
      case A1_28:
        this.sendCommand("R12");
        break;
      case A_64:
        this.sendCommand("R13");
        break;
      case A_32:
        this.sendCommand("R14");
        break;
      case A_16:
        this.sendCommand("R15");
        break;
      case AAUTO:
        this.sendCommand("R17");
        break;
      default:
        throw new UnsupportedOperationException("Not sure what to do with amp range " +
                                                ampRange + ".");
    }
  }

  /**
   * Gets the meter's voltage reading.
   *
   * @return the meter's voltage reading
   * @throws Exception on any exception sending the command or receiving its response
   */
  public String getVoltage() throws Exception {
    return this.sendCommandAndGetResults("V00");
  }

  /**
   * Gets the meter's current reading.
   *
   * @return the meter's current reading
   * @throws Exception on any exception sending the command or receiving its response
   */
  public String getCurrent() throws Exception {
    return this.sendCommandAndGetResults("V01");
  }

  /**
   * Gets the meter's watt reading.
   *
   * @return the meter's watt reading
   * @throws Exception on any exception sending the command or receiving its response
   */
  public String getWatt() throws Exception {
    return this.sendCommandAndGetResults("V02");
  }

  /**
   * Gets the meter's power factor (PF) reading.
   *
   * @return the meter's PF reading
   * @throws Exception on any exception sending the command or receiving its response
   */
  public String getPf() throws Exception {
    return this.sendCommandAndGetResults("V03");
  }

  /**
   * Gets the meter's frequency (Hz) reading.
   *
   * @return the meter's Hz reading
   * @throws Exception on any exception sending the command or receiving its response
   */
  public String getHz() throws Exception {
    return this.sendCommandAndGetResults("V04");
  }

  /**
   * Sends the given command to the meter.
   *
   * @param command the command to send
   * @throws Exception on any exception sending the command
   */
  private void sendCommand(String command) throws Exception {
    for (byte c : command.getBytes()) {
      this.port.sendSingleData((char) c);
    }

    this.port.sendSingleData(STOP_CHARACTER);
  }

  /**
   * Sends the given command to the meter and receives its response.
   *
   * @param command the command to send
   * @return the meter's response to the given command
   * @throws Exception on any exception sending the command or receiving its response
   */
  private String sendCommandAndGetResults(String command) throws Exception {
    this.sendCommand(command);

    StringBuilder builder = new StringBuilder();

    char next;

    while ((next = this.port.receiveSingleChar()) != STOP_CHARACTER) {
      builder.append(next);
    }

    return builder.toString();
  }

  public static void main(String[] args) throws Exception {
    Parameters parameters = new Parameters();
    parameters.setBaudRate("9600");
    parameters.setPort("COM1");
    parameters.setStopBits("1");
    parameters.setByteSize("8");
    GPM8212 reader = new GPM8212(new Com(parameters));
    
    while (true) {
      System.out.println(toExcelTime(new Date().getTime()) + ", " + reader.getCurrent());
      Thread.sleep(1000);
    }
  }

  private static double toExcelTime(long time) {
    return (time / 86400000.0) + 25569.0 - 0.291667;
  }
}
