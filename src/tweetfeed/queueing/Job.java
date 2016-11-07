/*
 * TweetFeed. Munier Parker, 2016.
 */
package tweetfeed.queueing;

/**
 *
 * @author Munier
 *
 * JOb defines all of the parameters needed to add to the JobConsumer from the JobProducer. It provides information and
 * instructions on how to process the job. Two variants are accommodated (a) A Job type is the kinds of jobs that the
 * server has been to defined to process and (b) instructions to the queue itself during runtime e.g. shutdown
 */
public class Job {

    public final static Object DEFAULT_TAG = new Object(); // In case a tag is null
    public final static JobType DEFAULT_MESSAGE = JobType.JOB_UNDEFINED; // null replacement for message
    public final static InstructionType DEFAULT_INSTRUCTION = InstructionType.INSTRUCTION_UNDEFINED; //same for instr.

    //An enumeration defining the different job types that the server can process
    public static enum JobType {
        JOB_UNDEFINED,
        JOB_QUEUE_INSTRUCTION,
        JOB_PARSE_INPUT,
        JOB_PRINT_TWEET_OUTPUT,
        JOB_PRINT_USER_FOLLOW_MAP
    };

    // These are the different instructions defined for the queue to execute on itself during runtime
    public static enum InstructionType {
        INSTRUCTION_UNDEFINED,
        INSTRUCTION_SHUTDOWN_ALL_CONSUMERS_IMMEDIATELY,
        INSTRUCTION_SHUTDOWN_THIS_CONSUMER_IMMEDIATELY
    };

    private JobType jobType; //the type of job for the instance
    private InstructionType instructionType; //the type of instruction for the instance
    private Object tag; //any additional information for the job to act on

    /**
     * Create a Job with a Content Type
     *
     * @param jobType see JobType Enumeration above
     */
    public Job(JobType jobType) {
        this(jobType, Job.DEFAULT_INSTRUCTION, Job.DEFAULT_TAG);
    }

    /**
     * Create a Job with a Content Type with a tag
     *
     * @param jobType see JobType Enumeration above
     * @param tag attach any user object to process
     */
    public Job(JobType jobType, Object tag) {
        this(jobType, Job.DEFAULT_INSTRUCTION, tag);
    }

    /**
     * Create a Job with an Instruction
     *
     * @param jobType See JobType Enumeration above
     * @param instructionType See InstructionType enumeration above
     */
    public Job(JobType jobType, InstructionType instructionType) {
        this(jobType, instructionType, Job.DEFAULT_TAG);
    }

    /**
     * Create a Job with an Instruction
     *
     * @param jobType See JobType Enumeration above
     * @param instructionType See InstructionType enumeration above
     * @param tag attach any user object to process
     */
    public Job(JobType jobType, InstructionType instructionType, Object tag) {
        this.jobType = jobType;
        this.instructionType = instructionType;
        this.tag = tag;
    }

    /**
     * A generic reference to any user object needed to during processing
     *
     * @param tag The object
     */
    public void setTag(Object tag) {
        if (tag == null) {
            this.tag = Job.DEFAULT_TAG;
        } else {
            this.tag = tag;
        }
    }

    /**
     * Get user definable tag needed during processing
     *
     * @return any Java Object
     */
    public Object getTag() {
        return this.tag;
    }

    /**
     * Get the JobType of this Job
     *
     * @return JobType
     */
    public JobType getJobType() {
        return this.jobType;
    }

    /**
     * Get instruction type of this job
     *
     * @return InstructionType
     */
    public InstructionType getInstructionType() {
        return this.instructionType;
    }
}
