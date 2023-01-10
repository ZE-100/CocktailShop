package com.z100.cocktailshop.util;

import com.z100.cocktailshop.exceptions.LoggerException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Predicate;


/**
 * A logger to append a supplied message to a log file.
 * Has parameter support: Use {} as a placeholder for each
 * of the supplied parameters.
 *
 * @since 1.0
 * @version 1.0
 */
//TODO Create custom annotation processor for this
public class Logger {

	private static final String FILE_PATH = "./data/log/cocktailshop.log";

	private static final String NEW_LINE = System.getProperty("line.separator");

	private final String timeMark;

	private  final Predicate<String> hasParams = msg ->
			msg.contains("{") && msg.contains("}");


	public Logger(String timeMark) {
		this.timeMark = timeMark;
	}

	/**
	 * Creates a new instance of the {@link Logger}
	 *
	 * @return new instance
	 */
	public static Logger getInstance() {

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return new Logger("[" + simpleDateFormat.format(new Date()) + "]\t-\t");
	}

	/**
	 * Appends the supplied exception's message along with
	 * its stacktrace to a log file
	 *
	 * @param exception The supplied exception
	 */
	public void log(Exception exception) {


		StringBuilder sb = new StringBuilder();
		sb.append(NEW_LINE);
		sb.append(timeMark);
		sb.append(exception.getMessage());

		Arrays.asList(exception.getStackTrace()).forEach(line -> {
			sb.append(NEW_LINE);
			sb.append(line);
		});

		writeToLogFile(sb.toString());
	}

	/**
	 * <p>
	 * Appends the supplied message along with possibly supplied
	 * parameters to a log file.
	 * <br/>
	 * Note: When used in combination with parameters please use {} as
	 * a placeholder for each supplied parameter.
	 * <br/>
	 * The placeholders for the parameters are replaced in order of the
	 * parameters being supplied.
	 * *The number of the placeholders and parameters should always match!*
	 * <br/>
	 * For further information regarding 'placeholders' in this context,
	 * please refer to the JavaDoc (or code) of
	 * {@link Logger#mapParamsToBrackets(String, Object[])}
	 * </p>.
	 *
	 * @param message The supplied message
	 * @param params Possible params (amount <= 0)
	 */
	public void log(String message, Object... params) {

		StringBuilder sb = new StringBuilder();
		sb.append(NEW_LINE);
		sb.append(timeMark);

		if (params == null || params.length == 0) {

			sb.append(message);

			writeToLogFile(sb.toString());

			return;
		}

		if (hasParams.test(message)) {
			sb.append(mapParamsToBrackets(message, params));
		}

		writeToLogFile(sb.toString());
	}

	/**
	 * <p>
	 * Maps the to-be-inserted parameters to their corresponding
	 * position in the message, marked by {}, in the order
	 * supplied to the {@link Logger#log(String, Object...)}
	 * method by replacing the placeholders -> {}.
	 * <br/>
	 * Note that this system will break if the placeholders
	 * are used incorrectly
	 * </p>
	 *
	 * @param message The supplied message
	 * @param params The supplied parameters
	 *
	 * @return A String with the placeholders being replaced by the parameters
	 */
	private static String mapParamsToBrackets(String message, Object[] params) {

		for (Object param : Arrays.asList(params)) {

			int indexBracket = message.indexOf("{");

			if (message.charAt(indexBracket + 1) == '}') {
				String firstPart = message.subSequence(0, indexBracket).toString();

				String secondPart = message.subSequence(indexBracket + 2, message.length()).toString();

				firstPart = firstPart + param;

				message = firstPart + secondPart;
			} else {
				throw new LoggerException("Incorrect usage of reserved placeholders");
			}
		}

		return message;
	}

	/**
	 * Appends the supplied message to a log file
	 *
	 * @param fullMessage The to-be-added message
	 */
	private void writeToLogFile(String fullMessage) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(Logger.FILE_PATH, true))) {

			writer.append(fullMessage);

		} catch (IOException e) {
			throw new LoggerException("Logger-append file not found");
		}
	}
}
