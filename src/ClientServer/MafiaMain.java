package ClientServer;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import GameEngine.GameEngine;
import Resources.GameMessage;
import Enumerators.CLInput;

/**
 * Server side of the communication
 * 
 * @author pacified
 *
 */
public class MafiaMain extends Thread {
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

	/**
	 * Start a new server thread
	 * 
	 * @param clientSoc
	 */
	private MafiaMain(Socket clientSoc) {
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

	/**
	 * Processes the line and gives output/feedback
	 * 
	 * @param inputLine
	 * @return 
	 * @return output/feedback
	 */
	private synchronized String processLine(String inputLine) {
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
		case ResetGame:
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
<<<<<<< HEAD
		case Daily:
			if (inputArray.length == 2) {
				outputLine = "Not implemented yet. Try again later.";
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
=======
>>>>>>> partially working
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
		case NewDay:
			if (inputArray.length == 3) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.Admin)) {
					outputLine = "Not yet implemented yet but yes he is.";
				} else {
					outputLine = GameMessage.BAD_INPUT();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case RebootGame:
			if (inputArray.length == 3) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.Admin)) {
					outputLine = "Not yet implemented yet but yes he is.";
				} else {
					outputLine = GameMessage.BAD_INPUT();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case RemovePlayer:
			if (inputArray.length == 2) {
				outputLine = GameEngine.removePlayer(inputArray[0]);
			} else if (inputArray.length == 4) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.AdminKey)) {
					outputLine = GameEngine.removePlayer(inputArray[3]);
				} else {
					outputLine = GameMessage.BAD_ADMIN_KEY();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case SeeVote:
			if (inputArray.length == 2) {
				outputLine = GameEngine.getVoteList();
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case ShowDaily:
			if (inputArray.length == 2) {
				outputLine = GameEngine.getDaily();
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case ShowLastWill:
			if (inputArray.length == 2) {
				outputLine = GameEngine.getLastWill(inputArray[0]);
			} else if (inputArray.length == 4) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.AdminKey)) {
					outputLine = GameEngine.getLastWill(inputArray[3]);
				} else {
					outputLine = GameMessage.BAD_ADMIN_KEY();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case ShowRole:
			if (inputArray.length == 2) {
				outputLine = GameEngine.getRole(inputArray[0]);
			} else if (inputArray.length == 4) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.AdminKey)) {
					outputLine = GameEngine.getRole(inputArray[3]);
				} else {
					outputLine = GameMessage.BAD_ADMIN_KEY();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case ShowTarget:
			if (inputArray.length == 2) {
				outputLine = GameEngine.getTarget(inputArray[0]);
			} else if (inputArray.length == 4) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.AdminKey)) {
					outputLine = GameEngine.getTarget(inputArray[3]);
				} else {
					outputLine = GameMessage.BAD_ADMIN_KEY();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case ShowVote:
			if (inputArray.length == 2) {
				outputLine = GameEngine.getVote(inputArray[0]);
			} else if (inputArray.length == 4) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.AdminKey)) {
					outputLine = GameEngine.getVote(inputArray[3]);
				} else {
					outputLine = GameMessage.BAD_ADMIN_KEY();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case StartGame:
			if (inputArray.length >= 3) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.AdminKey)) {
					if (inputArray.length == 3) {
						outputLine = GameEngine.start();
					} else {
						outputLine = "This option has not been implemented yet.";
					}
				} else {
					outputLine = GameMessage.BAD_ADMIN_KEY();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case Status:
			if (inputArray.length == 2) {
				return GameEngine.getStatus(1);
			} else if (inputArray.length == 3) {
				CLInput adminKey = CLInput.fromString(inputArray[2]);
				if (adminKey.equals(CLInput.AdminKey)) {
					outputLine = GameEngine.getStatus(2);
				} else {
					outputLine = GameMessage.BAD_ADMIN_KEY();
				}
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case Target:
			if (inputArray.length == 3 || inputArray.length == 4) {
				List<String> targetList = new ArrayList<String>();
				for (int i = 2; i < inputArray.length; i++) {
					targetList.add(inputArray[i]);
				}
				outputLine = GameEngine.setTarget(inputArray[0], targetList);
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		case Vote:
			if (inputArray.length == 3) {
				outputLine = GameEngine.setVote(inputArray[0], inputArray[2]);
			} else {
				outputLine = GameMessage.BAD_INPUT();
			}
			break;
		default:
			// outputLine = GameMessage.BAD_INPUT(1;
			break;
		}
		return outputLine;
	}
}