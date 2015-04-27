package ClientServer;

import java.net.*;
import java.io.*;

import GameEngine.GameEngine;
import GameEngine.GameMessage;
import Enumerators.CLInput;

public class Server extends Thread {
	protected static boolean serverContinue;
	protected Socket clientSocket;

	public static void main(String[] args) throws IOException {
		serverContinue = true;
		ServerSocket serverSocket = null;
		GameEngine.reset();

		try {
			serverSocket = new ServerSocket(10008);
			System.out.println("Connection Socket Created");
			try {
				while (serverContinue) {
					// serverSocket.setSoTimeout(10000);
					System.out.println("Waiting for Connection");
					try {
						new Server(serverSocket.accept());
					} catch (SocketTimeoutException ste) {
						System.out.println("Timeout Occurred");
					}
				}
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port: 10008.");
			System.exit(1);
		} finally {
			try {
				System.out.println("Closing Server Connection Socket");
				serverSocket.close();
			} catch (IOException e) {
				System.err.println("Could not close port: 10008.");
				System.exit(1);
			}
		}
		return;
	}

	private Server(Socket clientSoc) {
		clientSocket = clientSoc;
		start();
		try {
			join();
		} catch (InterruptedException e) {
			System.out.println("interrupt caught");
		}
	}

	public void run() {
		System.out.println("New Communication Thread Started");

		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			String inputLine;
			String outputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println("Client Input: " + inputLine);

				if (inputLine.equals("?")) {
					outputLine = new String("\"Bye.\" ends Client, "
							+ "\"End Server.\" ends Server");
				} else if (inputLine.equals("End Server.")) {
					outputLine = "Server shutting down.";
				} else if (inputLine.equals("Bye.")) {
					outputLine = "Disconnecting from this client.";
				} else {
					outputLine = processLine(inputLine);
				}

				out.println(outputLine + "\n" + "break");

				if (inputLine.equals("Bye.")) {
					break;
				}
				if (inputLine.equals("End Server.")) {
					serverContinue = false;
					break;
				}
			}

			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}
	}

	private String processLine(String inputLine) {
		String[] inputArray = inputLine.split(" ");
		String outputLine = "Something went wrong.";
		if (inputArray.length < 2) {
			return outputLine;
		}
		CLInput CLI = CLInput.fromString(inputArray[1]);
		switch (CLI) {
		case AddPlayer:
			if (inputArray.length == 3) {
				outputLine = GameEngine.registerPlayer(inputArray[2]);
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case BadInput:
			outputLine = GameMessage.BAD_INPUT();
			break;
		case ClearGame:
			if (inputArray.length == 3) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.Admin)) {
					outputLine = GameEngine.reset();
				} else {
					outputLine = GameMessage.BAD_INPUT();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case Daily:
			if (inputArray.length == 2) {
				outputLine = "Not implemented yet. Try again later.";
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case Help:
			if (inputArray.length == 2) {
				outputLine = GameMessage.HELP_MESSAGE();
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case LastWill:
			outputLine = "Not implemented yet. Try again later.";
			break;
		case ListAlive:
			if (inputArray.length == 2) {
				outputLine = "Not implemented yet. Try again later.";
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case ListPlayer:
			if (inputArray.length == 2) {
				outputLine = GameMessage.LIST_ALL_PLAYERS();
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case RemovePlayer:
			if (inputArray.length == 3) {
				outputLine = GameEngine.removePlayer(inputArray[2]);
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case ShowVote:
			if (inputArray.length == 2) {
				outputLine = "Not implemented yet. Try again later.";
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case StartGame:
			if (inputArray.length == 3) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.Admin)) {
					outputLine = "Not yet implemented yet and yes he is.";
				} else {
					outputLine = GameMessage.BAD_INPUT();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case Target:
			if (inputArray.length == 3 || inputArray.length == 4) {
				outputLine = "Not implemented yet. Try again later.";
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case Vote:
			if (inputArray.length == 3) {
				outputLine = "Not implemented yet. Try again later.";
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		default:
			// outputLine = GameMessage.BAD_INPUT();
			break;
		}
		return outputLine;
	}
}