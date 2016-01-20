//package com.mongo.morphia.complex;
//
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//import java.util.UUID;
//
//import org.apache.commons.lang.StringUtils;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.kingdee.cbos.ssp.base.session.Session;
//import com.kingdee.weibo.web.util.InvocationResult;
//import com.mongo.morphia.complex.Enum.ToxtConsumerType;
//import com.mongo.morphia.complex.Enum.UserStatus;
//import com.mongo.morphia.complex.basemodel.Network;
//import com.mongo.morphia.complex.basemodel.TagInfo;
//import com.mongo.morphia.complex.basemodel.User;
//import com.mongo.morphia.complex.basemodel.UserInfo;
//import com.mongo.morphia.complex.core.AccountType;
//import com.mongo.morphia.complex.integer.NetworkProfileInfo;
//import com.mongo.morphia.complex.model.AccountBindVO;
//import com.mongo.morphia.complex.model.Tag;
//import com.mongo.morphia.complex.model.UserService;
//import com.mongo.morphia.complex.model.inmodel.UserNetwork;
//import com.mongo.morphia.complex.model.inmodel.UserRepository;
//
//
//
//
//@Service("userService")
//public class UserServiceImpl implements UserService {
//
//	@Override
//	public User ensureUser(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateVerifiedEmail(String userId, Boolean verifiedEmail) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<User> getByEmail(String email, UserStatus status) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String audit(Collection<String> userIds) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void changed(String userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public long countDisableUsers() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public User createUser(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User createUnVerifiedUser(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void disableUser(Session session, String userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateDefaultNetwork(String userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void disableUser(String networkId, String userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void enableUser(String userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean exists(String email) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean existsByName(String name) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public List<User> findAll(int offset, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long findAllUserCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<User> findByDepartment(String depart, int start, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> findByDepartment(String defaultNetwork, String depart,
//			int start, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User findByEmail(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User findById(String id) {
//      User robot = this.getRobot();
//      if (robot.getId().equals(id)) {
//          return robot;
//      }
//
//      return userRepository.find(id);
//		
//	}
//
//	@Override
//	public List<User> findByIds(List<String> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> findByJobTitle(String title, int start, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> findByJobTitle(String defaultNetwork, String title,
//			int start, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User findByMobilePhone(String mobile) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> findByName(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User findBySpareEmail(String spareEmail) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> findDisableUsers(int offset, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> findUsers(UserStatus status, Date from, Date to,
//			int offset, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<NetworkProfileInfo> findWorkForRelation(String userId,
//			String networkId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User get(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getDepartment(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getJobTitle(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public UserNetwork getNetworkProfile(String userId, String networkId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User getRobot() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<TagInfo> getTags(String userId, String networkId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public UserInfo getUserInfo(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<NetworkProfileInfo> getWorkRelationFellow(String userId,
//			String networkId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<NetworkProfileInfo> getWorkRelationFollower(String userId,
//			String networkId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<NetworkProfileInfo> getWorkRelationSuperior(String userId,
//			String networkId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean hasSetWorkRelation(String userId, String networkId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isNetworkCreator(String userId, String networkId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void modifyPassword(String userId, String oldPassword,
//			String newPassword) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public User modifyUser(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String remove(Collection<String> userIds) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String remove(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void saveNetworkProfile(UserNetwork profile) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean modifyDefaultNetwork(String userId, String networkId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Network findUserDefaultNetworkByMail(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Network findUserDefaultNetworkByUserId(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Tag tagUser(String networkId, String operatorId, String tagName,
//			String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void untagUser(String networkId, String tagId, String userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public InvocationResult<User> authenticate(String accountName,
//			String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, Object> findNetWorkInvitation(String networkId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long countTeamUser(String networkId, String userType) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<User> getTeamUser(String networkId, String userType, int start,
//			int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User createDisableUser(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String removeDisableUser(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User saveDisableUser(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateUser(User user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isDevelop(String userId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void joinDevelop(String userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void cancelDevelop(String userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isFristActiveCompanyUser(String userId, Date fromDate) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void updateUserNoSynAddressbook(User user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<User> findEnableUserByIds(List<String> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long countByDefaultNetworkAndStatus(String defaultNetwork,
//			String status) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<User> getByDefaultNetworkAndStatus(String defaultNetwork,
//			String status, int start, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, String> createUserOfMobile(String mobile, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User modifyUser(User user, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateUnreadInviteCount(String userId, int unreadInviteCount) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void createNoticeOfOtherUserInvitation(User user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<User> findByModifyDate(Date yesterday, int offset, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> findByRegisterDate(Date yesterday, int offset, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, String> createUserOfThirdParty(AccountType accountType,
//			String openId, Map<String, Object> info) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, String> accountBind(String userId,
//			AccountType accountType, String account, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, String> preAccountBind(String userId,
//			AccountType accountType, String account, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, String> accountUnBind(String userId, String account) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateUnreadDisbandNetworkCount(String userId,
//			int unreadDisbandNetworkCount) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<AccountBindVO> findBindAccount(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateUnreadCount(String userId, int inviteCount,
//			int disbandNetworkCount) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean needSetPassword(String userId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean canSetPassword(String userId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean noEmailAndMobile(String userId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public String getOnlyOpenAccountType(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User createUserSourceXt(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long countUsers(UserStatus userStatus, boolean excludeTeamUser) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void modifyUserSourceXT(User user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public long countUsersForXT(UserStatus userStatus) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<User> getUsersForXT(UserStatus userStatus, int start, int limit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isCheckPassword(String userId, String password) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void sendXTAboutUser(User user, ToxtConsumerType type) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isKingdeeAdmin(Session session) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void disableUserSourceXT(String networkId, String userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public User getUserByXtId(String xtId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User getUserFromXt(String xtId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, String> xtResetMobileAccount(String account,
//			String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void onlyModifyXTPasswordAndPassword(User u, String xtPassword) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public User getUserByXtUserId(String xtUserId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> getAllUserByEmail(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> getAllUserByMobile(String mobile) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> findByNameAndNetwork(String name, String networkId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////    private final static Logger PLOG = LoggerFactory.getLogger("com.kingdee.sns.performance.log");
////    
////    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
////    
////    //因为有数据同步的问题，但是在初始化数据同步的时候不能给讯通发消息，只有增量同步时才能给讯通发消息，所以用它来区别是不是初次同步
////    private static final String OPERATIONFROM = "XT";
////    
////    @Autowired
////    private NetworkService networkService;
////
////    @Autowired
////    private TagService tagSvc;
////
////    @Autowired
////    private TagRelationService trSvc;
////
////    @Autowired
////    private NetworkRepository networkRepository;
////
////    @Autowired
////    private UserActivationRepository userActivationRepository;
////    @Autowired
////    private UserNetworkRepository userNetworkRepository;
////    @Autowired
////    private UserNetworkService userNetworkService;
////
//    @Autowired
//    private UserRepository userRepository;
////
////    @Autowired
////    private DailyUserStatService userStatService;
////
////    @Autowired
////    private AccountService accountService;
////
////    @Autowired
////    private ESPService espService;
////
////    @Autowired
////    private NetworkQuotaDAO quotaDAO;
////
////    @Autowired
////    private SnsUserService snsUserService;
////
////    @Autowired
////    private DomainRepository domainRepository;
////    
////    @Autowired
////    private DomainRelationService domainRelationService;
////    
////    @Autowired
////    private AddressBookService addressBookService;
////    
////    @Autowired
////    private EmailBindUserService emailBindUserService;
////    
////    @Autowired
////    private UserActivationService userActivationService;
////    
////    @Autowired
////    private ThreePlatformAccountService platformAccountService;
////    
////    @Autowired
////    private AccountBindService accountBindService;
////    
////    @Autowired
////    private AccountLogService accountLogService;
////    
////    @Autowired
////    private TokenService tokenService;
////    
////    @Autowired(required=false)
////    private SyncToxtMessageSender syncToxtMessageSender;
////    
////    @Autowired(required=false)
////    private AuthVerifyService authVerifyService;
////    
////    @Value("${auth.verify.isopen:false}")
////    private Boolean isOpen;  //集成第三方用户认证开关
////    
////    @Value("${auth.verify.adminUser}")
////    private String adminUserStr;
//// 
////    private static void addRelations(HashMap<String, Relation> relations, List<String> users, Relation r) {
////        if (users == null) {
////            return;
////        }
////        for (String id : users) {
////            relations.put(id, r);
////        }
////    }
////
////    @Override
////    public String audit(Collection<String> userIds) {
////        for (String uid : userIds) {
////            enableUser(uid);
////        }
////        return "OK";
////    }
////
////    @Override
////    public InvocationResult<User> authenticate(String accountName, String password) {
////        InvocationResult<User> result = new InvocationResult<User>();
////
////        if (accountName == null || accountName.trim().length() == 0) {
////            result.setSuccess(false);
////            ExceptionRecord record = new ExceptionRecord();
////            record.setMessage("账户为空！");
////            result.addException(record);
////            return result;
////        }
////        accountName = accountName.trim();
////
////        if (password == null || password.trim().length() == 0) {
////            result.setSuccess(false);
////
////            ExceptionRecord record = new ExceptionRecord();
////            record.setMessage("密码名为空！");
////            result.addException(record);
////
////            return result;
////        }
////        password = password.trim();
////
////        User user = null;
////
////        if (accountName.indexOf("@") > 0) {
////            User emailUser = this.userRepository.findByEmail(accountName);
//////            if (emailUser != null && emailUser.isVerifiedEmail()) {
////            if (emailUser != null) { //如果没有验证也让其通过
////                user = emailUser;
////            }
////        }
////
////        if (user == null && accountName.indexOf("@") == -1) {
////            User mobileUser = this.userRepository.findByMobilePhone(accountName);
////            if (mobileUser != null && mobileUser.isValidMobile()) {
////                user = mobileUser;
////            }
////            // lian_lin 2013-11-30 对于历史数据云员没有同步account表中，没办法登录问题
////            if(mobileUser==null){
////            	user = this.userRepository.getUserBySeq(accountName);
////            }
////        }
////
////        if (user == null && accountName.indexOf("@") == -1) {
////            return this.accountService.authenticate(AccountType.MOBILE, accountName, password);
////        }
////
////        if (user == null) {
////            result.setSuccess(false);
////
////            ExceptionRecord record = new ExceptionRecord();
////            record.setMessage("用户不存在！");
////            result.addException(record);
////
////            return result;
////        }
////
////        user = this.userRepository.findNoCache(user.getId());
//////        String passwordDigest = PasswordHelper.hashPassword(user.getId(), password);
//////        if (!passwordDigest.equals(user.getPassword())) {
////        Map<String,Object> resmap = new LinkedHashMap<String, Object>();
////		if(!isAdminUser(accountName) && isOpen){
////			try {
////				resmap = this.authVerifyService.verify(accountName, password);
////			} catch (Exception e) {
////				logger.error(e.getMessage());
////				result.setSuccess(false);
////	            ExceptionRecord record = new ExceptionRecord();
////	            record.setMessage(e.getMessage());
////	            result.addException(record);
////	            return result;
////			}
////		}
////        if(isAdminUser(accountName) || !this.isOpen){
//// 			if(!this.isCheckPassword(user.getId(), password)){
//// 			    result.setSuccess(false);
//// 			    ExceptionRecord record = new ExceptionRecord();
//// 			    record.setMessage("密码错误！");
//// 			    result.addException(record);
//// 			
//// 			    return result;
//// 	        }
////         }else{
////        	 if(resmap!=null && !Boolean.parseBoolean(resmap.get("success").toString())){
////         		result.setSuccess(false);
////         		ExceptionRecord record = new ExceptionRecord();
////         		record.setMessage("密码错误！");
////         		result.addException(record);
////         		return result;
////         	}
////         }
////        
////        result.setSuccess(true);
////        result.setResult(user);
////        return result;
////    }
////    
////    private boolean isAdminUser(String userName){
////		boolean flag = false;
////		if(org.springframework.util.StringUtils.hasText(adminUserStr)){
////			String[] adminUsers = adminUserStr.split(",");
////			for(String admin : adminUsers){
////				if(admin.equals(userName)){
////					flag = true;
////					break;
////				}
////			}
////		}
////		return flag;
////	}
////
////    /**
////     * @since 2014-04-28 云之家与讯通统一验证方法。以后密码验证统一调这个方法
////     * @author lian_lin
////     * @param userId
////     * @param password
////     * @return
////     */
////    @Override
////    public boolean isCheckPassword(String userId,String password){
////		boolean flag = false;
////		User user = this.get(userId);
////		String passwordDigest = PasswordHelper.hashPassword(user.getId(),
////				password);
////		// 判断云之家用户密码验证
////		if (passwordDigest.equals(user.getPassword())) {
////			flag = true;
////			return flag;
////		}
////		//用讯通算法判断密码有效性
//////		if(StringUtils.isNotBlank(user.getXtId())
//////				&&StringUtils.isNotBlank(user.getXtPassword())){
////		if(StringUtils.isNotBlank(user.getXtPassword())){
////			String xtPassword = user.getXtPassword();
////			String userPassword = "";
////			try{
//////				userPassword =  EncodeUtils.encode(user.getXtId, password);
////			  userPassword =  EncodeUtils.encode(user.getXtUserId(), password);
////			}catch(Exception e){
////				logger.error("xt encode password error",e);
////			}
////			String[] arrXtPassword = xtPassword.split(",");
////			for(String str:arrXtPassword){
////				if(userPassword.equals(str)){
////					flag = true;
////					break;
////				}
////			}
////		}
////		
////		//bug忘记提交，所以产生了一些从讯通过来的用户User上没有xtPassword,在此帮忙补上
////		if(!flag && StringUtils.isNotBlank(user.getXtId())) {
////			List<Account> accounts = this.accountService.findAccount(userId);
////			if(null != accounts && !accounts.isEmpty()) {
////				String userPassword = "";
////				try {
////					userPassword =  EncodeUtils.encode(user.getXtId(), password);
////				} catch (Exception e) {
////					logger.error("xt encode password error",e);
////				}
////				for (Account account : accounts) {
////					if(!StringUtils.isEmpty(userPassword) && userPassword.equals(account.getXtPassword())) {
////						flag = true;
////						user.setXtPassword(account.getXtPassword());
////						user.setOperationFrom("XT");
////						this.modifyUser(user);
////						break;
////					}
////				}
////			}
////		}
////		return flag;
////    }
////    
////    @Override
////    public void changed(String userId) {
////        CacheUtils.delete(userId);
////    }
////
////    @Override
////    public long countDisableUsers() {
////        return userRepository.countByStatus(UserStatus.DISABLE.name());
////    }
////
////    @Override
////    public long countTeamUser(String networkId, String userType) {
////        return userRepository.countTeamUser(networkId, userType);
////    }
////
////    @Override
////    public User createUnVerifiedUser(User user) {
////        user.setStatus(UserStatus.UNVERIFIED);
////        user.setVerifiedEmail(false);
////        
////        if(saveUser(user,true)){
////        	userStatService.addUser(user);// 记录新增用户
////        }
////        
////       	 sendXTAboutUser(user, ToxtConsumerType.TOXT_ADD_USER);
////      
////        return user;
////    }
////
////    @Override
////    public User createUser(User user) {
////        user.setStatus(UserStatus.APPLY);
//////        long g = System.currentTimeMillis();
////        if (saveUser(user,true)) {
//////        	long c = System.currentTimeMillis();
////            userStatService.addUser(user);
//////            long d = System.currentTimeMillis();
//////            logger.info("createUser_userStatService.addUse runtime:"+(d-c));
////        }
//////        long h = System.currentTimeMillis();
//////        logger.info("createUser_saveuser runtime:"+(h-g));
////        
////        //lian_lin 2014-04-18 用户是从讯通同步过来，就不执行以下逻辑
//////        if(!"xt".equals(user.getRegSource())){
////        
//////          long a = System.currentTimeMillis();
////          this.createNoticeOfOtherUserInvitation(user);
//////          long b = System.currentTimeMillis();
//////          logger.info("createUser_createNoticeOfOtherUserInvitation runtime:"+(b-a));
////          
//////        }
////          
//////         long e = System.currentTimeMillis();
////         this.accountService.syncUserToAccount(user.getId());
//////         long f = System.currentTimeMillis();
//////         logger.info("createUser_syncUserToAccount runtime:"+(e-f));
////         if(!"xt".equals(user.getRegSource()) && StringUtils.isEmpty(user.getXtId())) {
////        	 sendXTAboutUser(user, ToxtConsumerType.TOXT_ADD_USER);
////         }
////         
////        //lian_lin 2013-11-06 公共邮箱及手机主动注册，创建用户并没有defaultNetworkID，
////        //但是synchronizeNewUser必须得有defaultNetworkID.因此如果没有defaultNetworkID时，不执行以下业务
////        return user;
////    }
////    
////    
////    
////    
////    /**
////     * @since 2013-11-30 针对一个未注册用户，同时收到多个团队的邀请注册邮件。
////     * 当用户激活了一封邮件，系统给其他非激活的邮件产生云之家邀请通知
////     * @author lian_lin
////     * @param user
////     */
////    @Override
////    public void createNoticeOfOtherUserInvitation(User user){
////    	List<UserActivation> userActivations = null;
////    	//user居然会有空情况。
////    	if(user==null
////    			|| UserType.TEAM.equals(user.getUserType())
////    			|| UserType.THREEPLATFORM.equals(user.getUserType())){
////    		return;
////    	}
////    	if(user.isValidMobile()){
////    		 userActivations = this.userActivationService.findNoActiveAndRegByMobile(user.getMobile());
////    	}else{
////    		 userActivations = this.userActivationService.findNoActiveAndRegByEmail(user.getEmail());
////    	}
////    	
////    	
////    	Set<String> tmpSet = new HashSet<String>();
////    	//预定义网络，说明本次已经加进来了
////    	if(StringUtils.isBlank(user.getDefaultNetwork())){
////    	  tmpSet.add(user.getDefaultNetwork());
////    	}
////		for (UserActivation userActivation : userActivations) {
////			if(!tmpSet.contains(userActivation.getInviteToNetworkId())){
////				this.userNetworkService.sendInviteNotice(
////						userActivation.getInviterId(),
////						userActivation.getInviteToNetworkId(), user.getId());
////				tmpSet.add(userActivation.getInviteToNetworkId());
////			}
////		}
////    	
////    }
////    
////    /**
////     * 专门为讯通提供，其他人不要使用
////     * @param user
////     */
////    public void sendXTAboutUser(User user, ToxtConsumerType type){
////    	
////    	//从讯通那边触发的同步数据操作，不用再给讯通发消息
////    	if(OPERATIONFROM.equalsIgnoreCase(user.getOperationFrom())) {
////    		return;
////    	}
////    	
////    	/*if(ToxtConsumerType.TOXT_UPDATE_USER.name().equals(type.name())) {
////    		
////    		StackTraceElement stacks[] = Thread.currentThread().getStackTrace();
////    		
////    		if(null != stacks && stacks.length > 0) {
////    			String fangfadiaoyong = "fangfadiaoyong--";
////    			for (StackTraceElement stackTraceElement : stacks) {
////    				fangfadiaoyong += (stackTraceElement.getClassName() +"."+stackTraceElement.getMethodName()+"---");
////    			}
////        		logger.info(fangfadiaoyong);
////    		}
////    		
////    	}*/
////    	
////    	AddUserVO addUserVO = new AddUserVO();
////    	addUserVO.setName(user.getName());
////    	if(!StringUtils.isEmpty(user.getXtId())) {
////    		addUserVO.setUserId(user.getXtId());
////    	} else {
////    		addUserVO.setUserId(user.getId());
////    	}
////    	
////    	addUserVO.setDepartment(user.getDepartment());
////    	
////    	addUserVO.setEmail(user.getEmail());
////    	if(user.isVerifiedPublicEmail() && !StringUtils.isEmpty(user.getPublicEmail())) {
////    		addUserVO.setEmail(user.getEmail()+","+user.getPublicEmail());
////    	}
////    	String emails = "";
//////    	if(!StringUtils.isEmpty(user.getWorkMail())) {
//////    		emails = user.getWorkMail();
//////    	}
//////    	if(!StringUtils.isEmpty(user.getWorkMailExt())) {
//////    		if(!StringUtils.isEmpty(emails)) {
//////    			emails += ("," + user.getWorkMailExt());
//////    		} else {
//////    			emails = user.getWorkMailExt();
//////    		}
//////    	}
////    	emails = user.getWorkMailExt();  //同步邮箱(我的账号邮箱由此同步)
//////    	if(!StringUtils.isEmpty(emails)) {
////    	if(emails!=null) {		
////    		addUserVO.setEmails(emails);  //不知道是不是“，”分隔发现问题在改
////    	}
////    	
////    	if(null != user.getGender()) {
////    		addUserVO.setGender(user.getGender().toString());
////    	}
////    	addUserVO.setSyncType(type.name());
////    	addUserVO.setJobTitle(user.getJobTitle());
////    	
////    	addUserVO.setPhone(user.getMobile());
////    	
////    	//实在是不知道这个user上为啥没有密码啊，没有办法我只好到Account中去查
////    	if(StringUtils.isEmpty(user.getPassword())) {
////    		List<Account> accounts = accountService.findAccount(user.getId());
////    		if(null == accounts || accounts.isEmpty()) {
////    			logger.error("该用户没有账号 userId:"+user.getId());
////    			return ;
////    		} else {
////    			for (Account account : accounts) {
////    				if(!StringUtils.isEmpty(account.getPassword())) {
////    					addUserVO.setPassword(account.getPassword());
////    				}
////    				
////				}
////    			
////    		}
////    	} else {
////    		addUserVO.setPassword(user.getPassword());
////    	}
////    	
////    	String workMoblie = "";
////    	workMoblie = user.getWorkMobile();
////    	
////    	
////    	if(!StringUtils.isEmpty(user.getWorkMobileExt())) {
////    		if(!StringUtils.isEmpty(workMoblie)) {
////    			workMoblie += (","+user.getWorkMobileExt());   //先这样,因为没有说明我不知道是不是用","分隔
////    		} else {
////    			workMoblie = user.getWorkMobileExt();
////    		}
////    	}
////    	
////    	
////    	addUserVO.setPhones(workMoblie); 
////    	addUserVO.setOfficePhone1(user.getWorkPhone());		//增加办公电话同步
////    	addUserVO.setOfficePhone2(user.getWorkPhone2());	//增加办公电话2同步
////    	addUserVO.setBirthday(user.getBirthday());			//增加用户生日同步
////    	if(!StringUtils.isEmpty(user.getPhotoId())) {
////    		
////    		 String photoUrl = getPhotoUrl()+"space/c/photo/load?id="+user.getPhotoId();
////    		 addUserVO.setPhotoUrl(photoUrl);
////    	}
////    	
////    	addUserVO.setStatus(com.kingdee.sync.vo.MemberStatus.ACTIVE.toString());
////    	String uuId = UUID.randomUUID().toString();
////    	addUserVO.setId(uuId);
////    	syncToxtMessageSender.send(addUserVO);
////    	
////    	if(ToxtConsumerType.TOXT_ADD_USER.equals(type)) {
////    		accountService.sendToXTAboutModifyPassword(user.getId(), user.getPassword());
////    	}
////    }
////    
////    @Override
////    public User createDisableUser(User user) {
////        user.setStatus(UserStatus.DISABLE);
////        saveUser(user,true);
////        return user;
////    }
////
////    @Override
////    public User saveDisableUser(User user) {
////        User u = userRepository.findNoCache(user.getId());
////        if (u != null && UserStatus.DISABLE.equals(u.getStatus())) {
////           saveUser(user,false);
////           addressBookService.synchronize(user);
////        }
////        return user;
////    }
////
////    /*
////     * modified by xiangcai_li (non-Javadoc)
////     * 
////     * @see com.kingdee.cbos.user.UserService#disableUser(com.kingdee.cbos.ssp.base.session.Session,
////     * java.lang.String)
////     */
////    @Override
////    public void disableUser(Session session, String userId) {
////        this.disableUser(session.getNetworkId(), userId);
////    }
////
////    @Override
////    public void disableUser(String networkId, String userId) {
////        User user = userRepository.find(userId);
////        if (user != null) {
////        	//lian_lin 2013-11-29注销企业账户问题
////        	try{
////	        	Network network = this.networkService.get(networkId);
////	        	if(NetworkType.COMPANY.toString().equals(this.networkService.getNetworkRealType(network))
////	        			&&user.isVerifiedEmail()){
////	        		String domainName = this.domainRelationService.getDomainByEmail(user.getEmail());
////	                Domain domain = this.domainRepository.find(network.getDomainId());	
////	                if(domain.getIdentity().equals(domainName)){
//////	                	this.accountService.deleteAccount(user.getEmail());
////	                	//lian_lin 2014-02-11 通过账号名称删除账号。
////	                	this.accountService.deleteAccountByName(user.getEmail());
////	                	this.emailBindUserService.save(networkId,userId, user.getEmail(),String.valueOf(user.getUserSequence()));
////	                	
////	                	Cache cache = CacheFactory.getCache(User.class.getName());
////	                    cache.delete(user.getId());
////	                    cache.delete(user.getEmail());
////	                    String email = user.getEmail();
////	                	
////	                    
////	                  //lian_lin 2014-03-27 有时会出现在公司删除了用户，用户用企业邮箱还能登录进来。真是操蛋。日志打你现形
//////	                	 User delUser0 = this.userRepository.findByEmail(email); 
////	                	 
////	                	if(user.isPublicMailBox()){
////	                		user.setEmail(user.getPublicEmail());
////	                	}else{
////	                		user.setEmail(null);
////	                	}
////	                	 user.setVerifiedEmail(false);
////	                	 userRepository.save(user);
////	                	 addressBookService.synchronize(user);
////	                	//lian_lin 2014-03-27 有时会出现在公司删除了用户，用户用企业邮箱还能登录进来。真是操蛋。日志打你现形
//////	                	 User delUser = this.userRepository.findByEmail(email); 
//////	                	 if(delUser!=null){
//////	                		 logger.info("ssuupv....deletecacheusererror:"+email+"|new:email:"+user.getEmail()+" delUsero|"+(delUser0==null?"空":delUser0.getEmail()));
//////	                	 }
////	                }
////	        	}
////        	}catch(Exception e){
////        		logger.error("disableUser is errror:"+e.getMessage(),e);
////        	}
////        }
////    }
////    
////    
////    @Override
////    public void disableUserSourceXT(String networkId, String userId) {
////        User user = userRepository.find(userId);
////        if (user != null) {
////        	//lian_lin 2013-11-29注销企业账户问题
////        	try{
////	        	Network network = this.networkService.get(networkId);
////	        	if(NetworkType.COMPANY.toString().equals(this.networkService.getNetworkRealType(network))
////	        			&&user.isVerifiedEmail()){
////	        		String domainName = this.domainRelationService.getDomainByEmail(user.getEmail());
////	                Domain domain = this.domainRepository.find(network.getDomainId());	
////	                if(domain.getIdentity().equals(domainName)){
//////	                	this.accountService.deleteAccount(user.getEmail());
////	                	//lian_lin 2014-02-11 通过账号名称删除账号。
//////	                	this.accountService.deleteAccountByName(user.getEmail());
////	                	this.accountService.deleteAcountSourceXT(user.getEmail());
////	                	this.emailBindUserService.save(networkId,userId, user.getEmail(),String.valueOf(user.getUserSequence()));
////	                	
////	                	Cache cache = CacheFactory.getCache(User.class.getName());
////	                    cache.delete(user.getId());
////	                    cache.delete(user.getEmail());
////	                    String email = user.getEmail();
////	                	
////	                    
////	                  //lian_lin 2014-03-27 有时会出现在公司删除了用户，用户用企业邮箱还能登录进来。真是操蛋。日志打你现形
//////	                	 User delUser0 = this.userRepository.findByEmail(email); 
////	                	 
////	                	if(user.isPublicMailBox()){
////	                		user.setEmail(user.getPublicEmail());
////	                	}else{
////	                		user.setEmail(null);
////	                	}
////	                	 user.setVerifiedEmail(false);
////	                	 userRepository.save(user);
////	                	 addressBookService.synchronize(user);
////	                	//lian_lin 2014-03-27 有时会出现在公司删除了用户，用户用企业邮箱还能登录进来。真是操蛋。日志打你现形
//////	                	 User delUser = this.userRepository.findByEmail(email); 
//////	                	 if(delUser!=null){
//////	                		 logger.info("ssuupv....deletecacheusererror:"+email+"|new:email:"+user.getEmail()+" delUsero|"+(delUser0==null?"空":delUser0.getEmail()));
//////	                	 }
////	                }
////	        	}
////        	}catch(Exception e){
////        		logger.error("disableUser is errror:"+e.getMessage(),e);
////        	}
////        }
////    }
////    
////    @Override
////    public void enableUser(String userId) {
////        User user = userRepository.find(userId);
////        user.setStatus(UserStatus.ENABLE);
////        userRepository.save(user);
////        addressBookService.synchronize(user);
////    }
////
////    @Override
////    public boolean exists(String email) {
////        return findByEmail(email) != null;
////    }
////
////    @Override
////    public boolean existsByName(String name) {
////        List<User> users = findByName(name);
////        return users != null && users.size() > 0;
////    }
////
////    @Override
////    public List<User> findAll(int offset, int limit) {
////        return userRepository.findAll(offset, limit);
////    }
////
////    @Override
////    public long findAllUserCount() {
////        return userRepository.findAllUserCount();
////    }
////
////    @Override
////    public List<User> findByDepartment(String depart, int start, int limit) {
////        return userRepository.findByDepartment(depart, start, limit);
////    }
////
////    @Override
////    public List<User> findByDepartment(String defaultNetwork, String depart, int start, int limit) {
////        return userRepository.findByDepartment(defaultNetwork, depart, start, limit);
////    }
////
////    @Override
////    public User findByEmail(String email) {
////    	User user =  userRepository.findByEmail(email);
////    	//lian_lin 2013-11-29 同步生成各个用户加入网络的通讯录接口，公共邮箱没有存在email字段上，
////    	//需要通过Account反找到User对象 
////    	if(user==null){
////    		Account account = this.accountService.getAccountByName(email);
////    		if(account!=null){
////    			user = this.get(account.getUser());
////    			if(user!=null && !StringUtils.isEmpty(user.getEmail())){
////    				user.setEmail(email);
////    				this.updateUserNoSynAddressbook(user);
////    			}
////    		}
////    	}
////    	return user;
////    }
////
////    @Override
////    public User findById(String id) {
////        User robot = this.getRobot();
////        if (robot.getId().equals(id)) {
////            return robot;
////        }
////
////        return userRepository.find(id);
////    }
////
////    @Override
////    public List<User> findByIds(List<String> ids) {
////        return userRepository.find(ids);
////    }
////    
////    /**
////     * lian_lin 2013-09-22
////     * @param userIds 待查询用户ID
////     * @return 指定用户ids，激活状态的的User集合
////     */
////    @Override
////    public List<User> findEnableUserByIds(List<String> ids) {
////        return userRepository.findActiveAndUserIds(ids);
////    }
////    
////    
////    
////    @Override
////    public List<User> findByJobTitle(String title, int start, int limit) {
////        return userRepository.findByJobTitle(title, start, limit);
////    }
////
////    @Override
////    public List<User> findByJobTitle(String defaultNetwork, String title, int start, int limit) {
////        return userRepository.findByJobTitle(defaultNetwork, title, start, limit);
////    }
////
////    @Override
////    public User findByMobilePhone(String mobile) {
////        if (StringUtils.isEmpty(mobile)) {
////            return null;
////        }
////        return userRepository.findByMobilePhone(mobile);
////    }
////
////    @Override
////    public List<User> findByName(String name) {
////        return userRepository.findByName(name);
////    }
////    
////    @Override
////    public List<User> findByNameAndNetwork(String name,String networkId) {
////    	//查找全网络内同名的人，私有云这样处理理论上问题不大
////    	List<User> sameNameUsers = userRepository.findByRealName(name);
////     	if(sameNameUsers != null){
////	    	for (int i = 0; i < sameNameUsers.size(); i++) {
////	    		User user = sameNameUsers.get(i);
////	    		UserNetwork un = userNetworkRepository.get(user.getId(), networkId);
////	    		if(un == null){//证明此人不在此网络内
////	    			sameNameUsers.remove(i);
////	    			i--;
////	    		}
////			}
////     	}
////        return sameNameUsers;
////    }
////
////    @Override
////    public User findBySpareEmail(String spareEmail) {
////        return userRepository.findBySpareEmail(spareEmail);
////    }
////
////    @Override
////    public List<User> findDisableUsers(int offset, int limit) {
////        return userRepository.findByStatus(UserStatus.DISABLE.name(), offset, limit);
////    }
////    
////    @Override
////    public List<User> findUsers(UserStatus status, Date from, Date to, int offset, int limit){
////        return userRepository.findByStatus(status.name(), from, to, offset, limit);
////    }
////    
////    @Override
////    public List<User> findByModifyDate(Date yesterday, int offset, int limit){
////    	return userRepository.findByModifyDate(yesterday, offset, limit);
////    }
////    
////    @Override
////    public List<User> findByRegisterDate(Date yesterday, int offset, int limit){
////    	return userRepository.findByRegisterDate(yesterday, offset, limit);
////    }
////    
////    @Override
////    public Map<String, Object> findNetWorkInvitation(String networkId) {
////        Map<String, Object> results = null;
////        int inviterCount = 0; // 邀请者数量
////        int inviteeCount = 0; // 被邀请者数量
////
////        int pageSize = 100;
////        long userCounts = userNetworkService.getUserCountByNetworkId(networkId, false);
////        if (userCounts <= 0) {
////            return null;
////        }
////        Network neworkQuery = networkService.get(networkId);
////        String domain = neworkQuery.getSubDomainName();
////        if (StringUtils.isEmpty(domain)) {
////            return results;
////        }
////        for (int start = 0; start < userCounts; start += pageSize) {
////            List<User> users = userNetworkService.findAllUsersByNetworkId(networkId, start, pageSize, false, false);
////            if (users != null && users.size() > 0) {
////                for (User user : users) {
////                    String inviterId = user.getId();
////
////                    ActivationType type = null;
////                    String candidateEmail = null;
////                    String candidateEmailDomain = domain;
////                    Boolean sameDomain = false;
////                    String destinationId = null;
////                    Boolean inviteeExist = null;
////                    Boolean inviteeCreated = Boolean.TRUE;
////                    UserActivationStatus status = UserActivationStatus.ACTIVED;
////                    String lastInvitTimeOperator = null;
////                    Date lastInvitTime = null;
////                    int offset = 0;
////                    int limit = 0;
////                    List<UserActivation> uas = this.userActivationRepository.getUserActivations(type, networkId,
////                            inviterId, candidateEmail, candidateEmailDomain, sameDomain, destinationId, inviteeExist,
////                            inviteeCreated, status, lastInvitTimeOperator, lastInvitTime, offset, limit);
////
////                    if (uas != null && uas.size() > 0) {
////                        int ic = 0;
////                        for (UserActivation ua : uas) {
////                            String email = ua.getEmail();
////                            String inviteeId = ua.getInviteeId();
////                            User invitee = userRepository.find(inviteeId);
////                            invitee = userRepository.findByEmail(email);
////                            if (invitee == null) {
////                                continue;
////                            }
////                            List<Network> networks = userNetworkService.getUserNetworks(inviteeId);
////                            for (Network network : networks) {
////                                if (!networkId.equals(network.getId()) && NetworkType.COMPANY.equals(network.getType())) {
////                                    ic = ic + 1;
////                                    break;
////                                }
////                            }
////                        }
////                        if (ic > 0) {
////                            inviterCount = inviterCount + 1;
////                            inviteeCount = inviteeCount + ic;
////                        }
////                    }
////                }
////                results = new HashMap<String, Object>();
////                results.put("inviterCount", inviterCount);
////                results.put("inviteeCount", inviteeCount);
////            }
////        }
////        return results;
////    }
////
////    @Override
////    public Network findUserDefaultNetworkByMail(String email) {
////        User user = userRepository.findByEmail(email);
////        if (user != null) {
////            return findUserDefaultNetworkByUserId(user.getId());
////        }
////        return null;
////    }
////
////    /**
////     * 获取用户默认的网络（公司）
////     */
////    @Override
////    public Network findUserDefaultNetworkByUserId(String userId) {
////        // FIXME 目前是查询所有网络，后续可以冗余存储在User对象上
////        List<Network> userNetworks = userNetworkService.getUserNetworks(userId, NetworkType.COMPANY);
////        if (userNetworks != null && userNetworks.size() > 0) {
////            return userNetworks.get(0);
////        }
////        return null;
////    }
////
////    @Override
////    public List<NetworkProfileInfo> findWorkForRelation(String userId, String networkId) {
////        if (PLOG.isDebugEnabled()) {
////            PLOG.debug("UserServiceImpl.findWorkForRelation");
////        }
////        LinkedList<NetworkProfileInfo> relations = new LinkedList<NetworkProfileInfo>();
////        HashSet<String> refs = new HashSet<String>(32);
////
////        // 自己
////        UserNetwork my_profile = userNetworkService.getUserNetworkWithRelation(userId, networkId);
////        addNetworkProfileInfo(refs, relations, my_profile, Relation.SELF, false);
////
////        // 同事与上级
////        DAO<NetworkProfileRelation> daor = DAOFactory.getInstance(NetworkProfileRelation.class);
////        if (my_profile.getId() != null) {
////            Results<NetworkProfileRelation> rsr = daor.query(daor.getCriteriaQueryBuilder().createCriteriaQuery()
////                    .filter("profileId", my_profile.getId()));
////            while (rsr.hasNext()) {
////                NetworkProfileRelation r = rsr.next();
////                UserNetwork profile = getNetworkProfile(networkId, userNetworkRepository, r);
////                if (profile != null) {
////                    if (profile.getNetworkId().equals(networkId)) {
////                        addNetworkProfileInfo(refs, relations, profile, r.getRelation(), true);
////                    }
////                }
////            }
////        }
////
////        // 其他人指定我为同事
////        Results<NetworkProfileRelation> rsr = daor.query(daor.getCriteriaQueryBuilder().createCriteriaQuery()
////                .filter("userId", userId).filter("relation", Relation.FELLOW.name()));
////        while (rsr.hasNext()) {
////            NetworkProfileRelation r = rsr.next();
////            UserNetwork profile = userNetworkRepository.find(r.getProfileId());
////            if (profile != null) {
////                if (profile.getNetworkId().equals(networkId)) {
////                    addNetworkProfileInfo(refs, relations, profile, Relation.FELLOW, false);
////                }
////            }
////        }
////
////        // 下级
////        rsr = daor.query(daor.getCriteriaQueryBuilder().createCriteriaQuery().filter("userId", userId)
////                .filter("relation", Relation.SUPERIOR.name()));
////        while (rsr.hasNext()) {
////            NetworkProfileRelation r = rsr.next();
////            UserNetwork profile = userNetworkRepository.find(r.getProfileId());
////            if (profile != null) {
////                if (profile.getNetworkId().equals(networkId)) {
////                    addNetworkProfileInfo(refs, relations, profile, Relation.FOLLOWER, false);
////                }
////            }
////        }
////
////        return relations;
////    }
////
////    @Override
////    public User get(String id) {
////        User robot = this.getRobot();
////        if (robot.getId().equals(id)) {
////            return robot;
////        }
////
//////        if (PLOG.isDebugEnabled()) {
//////            PLOG.debug("UserServiceImpl.get:" + id);
//////        }
////        return userRepository.find(id);
////    }
////
////    @Override
////    public List<User> getByEmail(String email, UserStatus status) {
////        return this.userRepository.getByEmail(email, status);
////    }
////
////    @Override
////    public String getDepartment(String userId) {
////        User u = this.get(userId);
////        if (u != null) {
////            return u.getDepartment();
////        }
////        return null;
////    }
////
////    @Override
////    public String getJobTitle(String userId) {
////        User u = this.get(userId);
////        if (u != null) {
////            return u.getJobTitle();
////        }
////        return null;
////    }
////
////    @Override
////    public UserNetwork getNetworkProfile(String userId, String networkId) {
////        if (PLOG.isDebugEnabled()) {
////            PLOG.debug("UserServiceImpl.getNetworkProfile");
////        }
////        return userNetworkService.getUserNetworkWithRelation(userId, networkId);
////    }
////
////    @Override
////    public User getRobot() {
////        User robot = new User();
////        robot.setId(User.ROBOT_ID);
////        robot.setName("云之家助手");
////        robot.setPhotoId("000000000000000000000000");
////        return robot;
////    }
////
////    @Override
////    public List<TagInfo> getTags(String userId, String networkId) {
////        ArrayList<TagInfo> tags = new ArrayList<TagInfo>();
////        for (TagRelation r : trSvc.findTagRelationsByBusinessObjectId(networkId, APP_ID, BO_TYPE, userId)) {
////            Tag t = tagSvc.getTagById(r.getTagId());
////            TagInfo info = new TagInfo(r.getTagId(), t.getName(), r.getUserId(), null);
////            tags.add(info);
////        }
////        return tags;
////    }
////
////    @Override
////    public List<User> getTeamUser(String networkId, String userType, int start, int limit) {
////
////        List<User> list = userRepository.getTeamSnsUser(networkId, userType, start, limit);
////
////        return list;
////    }
////
////    @Override
////    public UserInfo getUserInfo(String userId) {
////        return CacheUtils.get(userId);
////    }
////
////    // 同事
////    @Override
////    public List<NetworkProfileInfo> getWorkRelationFellow(String userId, String networkId) {
////        if (PLOG.isDebugEnabled()) {
////            PLOG.debug("UserServiceImpl.getWorkRelationFellow");
////        }
////
////        LinkedList<NetworkProfileInfo> relations = new LinkedList<NetworkProfileInfo>();
////        HashSet<String> refs = new HashSet<String>(32);
////
////        UserNetwork my_profile = userNetworkService.getUserNetworkWithRelation(userId, networkId);
////
////        DAO<NetworkProfileRelation> daor = DAOFactory.getInstance(NetworkProfileRelation.class);
////        if (my_profile.getId() != null) {
////            Results<NetworkProfileRelation> rsr = daor.query(daor.getCriteriaQueryBuilder().createCriteriaQuery()
////                    .filter("profileId", my_profile.getId()));
////            while (rsr.hasNext()) {
////                NetworkProfileRelation r = rsr.next();
////                if (Relation.FELLOW.equals(r.getRelation())) {
////                    UserNetwork profile = getNetworkProfile(networkId, userNetworkRepository, r);
////                    if (profile != null) {
////                        if (profile.getNetworkId().equals(networkId)) {
////                            addNetworkProfileInfo(refs, relations, profile, r.getRelation(), true);
////                        }
////                    }
////                }
////            }
////        }
////
////        // 其他人指定我为同事
////        Results<NetworkProfileRelation> rsr = daor.query(daor.getCriteriaQueryBuilder().createCriteriaQuery()
////                .filter("userId", userId).filter("relation", Relation.FELLOW.name()));
////        while (rsr.hasNext()) {
////            NetworkProfileRelation r = rsr.next();
////            UserNetwork profile = userNetworkRepository.find(r.getProfileId());
////            if (profile != null) {
////                if (profile.getNetworkId().equals(networkId)) {
////                    addNetworkProfileInfo(refs, relations, profile, Relation.FELLOW, false);
////                }
////            }
////        }
////
////        return relations;
////    }
////
////    @Override
////    public List<NetworkProfileInfo> getWorkRelationFollower(String userId, String networkId) {
////        if (PLOG.isDebugEnabled()) {
////            PLOG.debug("UserServiceImpl.getWorkRelationFollower");
////        }
////        LinkedList<NetworkProfileInfo> relations = new LinkedList<NetworkProfileInfo>();
////        HashSet<String> refs = new HashSet<String>(32);
////
////        DAO<NetworkProfileRelation> daor = DAOFactory.getInstance(NetworkProfileRelation.class);
////
////        // 下级
////        Results<NetworkProfileRelation> rsr = daor.query(daor.getCriteriaQueryBuilder().createCriteriaQuery()
////                .filter("userId", userId).filter("relation", Relation.SUPERIOR.name()));
////        while (rsr.hasNext()) {
////            NetworkProfileRelation r = rsr.next();
////            UserNetwork profile = userNetworkRepository.find(r.getProfileId());
////            if (profile != null) {
////                if (profile.getNetworkId().equals(networkId)) {
////                    addNetworkProfileInfo(refs, relations, profile, Relation.FOLLOWER, false);
////                }
////            }
////        }
////
////        return relations;
////    }
////
////    // added by mgl 2011-1-12
////    // 上级
////    @Override
////    public List<NetworkProfileInfo> getWorkRelationSuperior(String userId, String networkId) {
////        if (PLOG.isDebugEnabled()) {
////            PLOG.debug("UserServiceImpl.getWorkRelationSuperior");
////        }
////        LinkedList<NetworkProfileInfo> relations = new LinkedList<NetworkProfileInfo>();
////        HashSet<String> refs = new HashSet<String>(32);
////
////        // 自己
////        UserNetwork my_profile = userNetworkService.getUserNetworkWithRelation(userId, networkId);
////
////        DAO<NetworkProfileRelation> daor = DAOFactory.getInstance(NetworkProfileRelation.class);
////        if (my_profile.getId() != null) {
////            Results<NetworkProfileRelation> rsr = daor.query(daor.getCriteriaQueryBuilder().createCriteriaQuery()
////                    .filter("profileId", my_profile.getId()));
////            while (rsr.hasNext()) {
////                NetworkProfileRelation r = rsr.next();
////                if (Relation.SUPERIOR.equals(r.getRelation())) {
////                    UserNetwork profile = getNetworkProfile(networkId, userNetworkRepository, r);
////                    if (profile != null) {
////                        if (profile.getNetworkId().equals(networkId)) {
////                            addNetworkProfileInfo(refs, relations, profile, r.getRelation(), true);
////                        }
////                    }
////                }
////            }
////        }
////
////        return relations;
////    }
////
////    @Override
////    public boolean hasSetWorkRelation(String userId, String networkId) {
////        if (PLOG.isDebugEnabled()) {
////            PLOG.debug("UserServiceImpl.hasSetWorkRelation");
////        }
////
////        String profile = userNetworkRepository.get(userId, networkId).getId();
////        DAO<NetworkProfileRelation> daor = DAOFactory.getInstance(NetworkProfileRelation.class);
////        long count = daor.count(daor.getCriteriaQueryBuilder().createCriteriaQuery().filter("profileId", profile));
////        return count > 0;
////    }
////
////    @Override
////    public boolean isNetworkCreator(String userId, String networkId) {
////        Network network = networkService.get(networkId);
////        if (userId.equals(network.getCreatorId())) {
////            return true;
////        }
////        return false;
////    }
////
////    @Override
////    public boolean modifyDefaultNetwork(String userId, String networkId) {
////        User user = userRepository.find(userId);
////        if (user != null) {
////            user.setDefaultNetwork(networkId);
////            userRepository.save(user);
////            addressBookService.modifyDefaultNetwork(user);
////            return true;
////        }
////        return false;
////    }
////
////    @Override
////    public void modifyPassword(String userId, String oldPassword, String newPassword) {
////        User user = userRepository.find(userId);
////
////        
////        // 如果旧密码不为NULL，则检查旧密码是否相符
////        if (!StringUtils.isEmpty(oldPassword)) {
////            
////            if (!isCheckPassword(userId, oldPassword)) {
////                throw new OldPasswordErrorException("旧密码错误");
////            }
////        } 
////        // 修改密码时，对其密码进行哈希转换
////        String hashed = PasswordHelper.hashPassword(user.getId(), newPassword);
////        user.setPassword(hashed);
////        //设置是否重置密码为false，管理员在设置别人密码是，应注意是否需要提示用户设置密码
////        user.setResetPwd(false);
////        user.setXtPassword("");
////        userRepository.save(user);
////        accountService.modifyXTPassword(user.getId(), "");
////        accountService.syncUserToAccount(userId);
////        accountService.sendToXTAboutModifyPassword(userId, hashed);
////        
////        //修改密码后更新用户各端token
////        tokenService.changeUserAppToken(userId);
////        //修改密码同步到 cmcloud 中
////        try{
////        	this.platformAccountService.synPasswd2CMCloud(user);
////        }catch(Exception e) {
////        	logger.error("同步到云平台出错:"+e);
////        }
////    }
////
////    @Override
////    public User modifyUser(User user) {
////        // 修改用户时,用户的ID和密码不能变化(修改密码需要用modifyPassword)
////    	saveUser(user,false);
////    	sendXTAboutUser(user, ToxtConsumerType.TOXT_UPDATE_USER);
////        userNetworkService.updateName(user.getId(), user.getName());
////        //讯通全扫描同步，不加到通讯录
////        if(!"XT".equalsIgnoreCase(user.getOperationFrom())){
////          addressBookService.synchronize(user);
////        }
////        
////        return user;
////    }
////    
////    /**
////     * @since  2013-11-28 只用于手机激活时，设置密码
////     * @author lian_lin
////     * @param user
////     * @param password
////     * @return
////     */
////    @Override
////    public User modifyUser(User user,String password) {
////        // 修改用户时,用户的ID和密码不能变化(修改密码需要用modifyPassword)
////        String hashed = PasswordHelper.hashPassword(user.getId(), password);
////        user.setPassword(hashed);
////        user.setXtPassword("");
////        User existUser = get(user.getId());
////        if (existUser != null) {
////            if (existUser.extended() != null) {
////                user.putAll(existUser.extended());
////            }
////            if (existUser.getRegisterDate() != null)
////                user.setRegisterDate(existUser.getRegisterDate());
////            
////            if(StringUtils.isEmpty(user.getEmail())){
////            	user.setEmail(existUser.getEmail());
////            }
////            
////            if(StringUtils.isEmpty(user.getMobile())){
////            	user.setMobile(existUser.getMobile());
////            }
////        } else {
////            throw new RuntimeException("User["+user.getId()+"] not found!");
////        }
////        
////        userRepository.save(user);
////        accountService.modifyXTPassword(user.getId(), "");
////        userNetworkService.updateName(user.getId(), user.getName());
////        addressBookService.synchronize(user);
////        sendXTAboutUser(user, ToxtConsumerType.TOXT_UPDATE_USER);
////        return user;
////    }
////    
////    
////
////    @Override
////    public String remove(Collection<String> userIds) {
////        for (String uid : userIds) {
////            User user = userRepository.find(uid);
////            if (user != null) {
////                userRepository.delete(uid);
////                userStatService.deleteUser(user);
////            }
////        }
////        return "OK";
////    }
////
////    @Override
////    public String remove(String userId) {
////        User user = userRepository.find(userId);
////        if (user != null) {
////            userRepository.delete(userId);
////            userStatService.deleteUser(user);
////        }
////        return "OK";
////    }
////
////    @Override
////    public String removeDisableUser(String userId) {
////        User user = userRepository.find(userId);
////        if (user != null && UserStatus.DISABLE.equals(user.getStatus())) {
////            userRepository.delete(userId);
////        }
////        return "OK";
////    }
////
////    @Override
////    public void saveNetworkProfile(UserNetwork profile) {
////        if (PLOG.isDebugEnabled()) {
////            PLOG.debug("UserServiceImpl.saveNetworkProfile");
////        }
////        // 保存网络档案
////        profile.setUpdateTime(new Date());
////        userNetworkService.save(profile);
////
////        // 预处理汇报关系
////        HashMap<String, Relation> relations = new HashMap<String, Relation>();
////        addRelations(relations, profile.getSuperiors(), Relation.SUPERIOR);
////        addRelations(relations, profile.getFellows(), Relation.FELLOW);
////
////        // 保存汇报关系
////        DAO<NetworkProfileRelation> dao = DAOFactory.getInstance(NetworkProfileRelation.class);
////        CriteriaQuery<NetworkProfileRelation> query = dao.getCriteriaQueryBuilder().createCriteriaQuery()
////                .filter("profileId", profile.getId());
////
////        // 修改或删除已存在的汇报关系
////        for (NetworkProfileRelation npr : dao.query(query).asList()) {
////            String userId = npr.getUserId();
////            Relation r = relations.get(userId);
////            if (r == null) {
////                dao.delete(npr.getId());
////            } else {
////                npr.setRelation(r);
////                dao.save(npr);
////                relations.remove(userId);
////            }
////        }
////        // 新增汇报关系
////        for (Entry<String, Relation> entry : relations.entrySet()) {
////            NetworkProfileRelation r = new NetworkProfileRelation(profile.getId(), entry.getKey(), entry.getValue());
////            dao.save(r);
////        }
////    }
////
////    @Override
////    public Tag tagUser(String networkId, String operatorId, String tagName, String userId) {
////        tagName = (tagName == null) ? "" : tagName.trim();
////        Tag tag = this.tagSvc.tagObject(networkId, operatorId, tagName, APP_ID, BO_TYPE, userId);
////
////        UserNetwork userNetwork = this.userNetworkService.getUserNetwork(userId, networkId);
////        if (userNetwork == null) {
////            return tag;
////        }
////
////        @SuppressWarnings("unchecked")
////        List<String> tags = (List<String>) userNetwork.get("tags");
////        if (tags == null) {
////            tags = new ArrayList<String>();
////            userNetwork.put("tags", tags);
////        }
////
////        if (!tags.contains(tagName)) {
////            tags.add(tagName);
////        }
////
////        this.userNetworkService.save(userNetwork);
////
////        return tag;
////    }
////
////    @Override
////    public void untagUser(String networkId, String tagId, String userId) {
////        // 不管当前在公司还是在社区，自己可以删除任一网络下的自己的标签；自己可以删除任一网络下自己给别人打的标签
////        this.tagSvc.untagObject(null, tagId, APP_ID, BO_TYPE, userId);
////
////        // UserNetwork userNetwork = this.userNetworkService.getUserNetwork(userId, networkId);
////        // if (userNetwork == null) {
////        // return;
////        // }
////
////        Tag tag = this.tagSvc.getTagById(tagId);
////        if (tag == null) {
////            return;
////        }
////        String name = tag.getName();
////        name = (name == null) ? "" : name.trim();
////        List<UserNetwork> userNetworks = userNetworkService.getByUserIdAndType(userId, null, null, 0, 0);
////        for (UserNetwork userNetwork : userNetworks) {
////            @SuppressWarnings("unchecked")
////            List<String> tags = (List<String>) userNetwork.get("tags");
////            if (tags == null || tags.isEmpty()) {
////                return;
////            }
////
////            if (!tags.contains(name)) {
////                return;
////            }
////            tags.remove(name);
////            this.userNetworkService.save(userNetwork);
////        }
////    }
////
////    private void addNetworkProfileInfo(HashSet<String> refs, LinkedList<NetworkProfileInfo> relations, //
////            UserNetwork profile, Relation r, boolean assigned) {
////        String userId = profile.getUserId();
////        if (refs.contains(userId)) {
////            return;
////        } else {// 关联用户只加入到上级/同事/下级之一.
////            refs.add(userId);
////        }
////
////        User user = get(userId);
////        if (user != null) {
////            NetworkProfileInfo info = new NetworkProfileInfo(user.getId(), //
////                    user.getName(), user.getPhotoId(), ""/* profile.getJobTitle() */, //
////                    r, assigned, user.getEmail());
////            relations.add(info);
////        }
////    }
////
////    private UserNetwork getNetworkProfile(String networkId, UserNetworkRepository dao, NetworkProfileRelation r) {
////        if (PLOG.isDebugEnabled()) {
////            PLOG.debug("UserServiceImpl.getNetworkProfile.private");
////        }
////
////        UserNetwork profile = dao.get(r.getUserId(), networkId);
////        return profile;
////    }
////
////    @Override
////    public void updateVerifiedEmail(String userId, Boolean verifiedEmail) {
////        if (StringUtils.isEmpty(userId)) {
////            String msg = "\"userId\" can't be empty!";
////            throw new IllegalArgumentException(msg);
////        }
////
////        User user = this.userRepository.find(userId);
////        if (user != null) {
////            user.setVerifiedEmail(verifiedEmail);
////            this.userRepository.save(user);
////        }
////    }
////
////    @Override
////    public void updateUser(User user) {
////        User u = userRepository.findNoCache(user.getId());
////
////        saveUser(user,false);
////
////        if (u != null && user.getName() != null && !user.getName().equals(u.getName())) {
////            userNetworkService.updateName(user.getId(), user.getName());
////        }
////
////        sendXTAboutUser(user, ToxtConsumerType.TOXT_UPDATE_USER);
////        addressBookService.synchronize(user);
////        if(!StringUtils.isEmpty(user.getPassword())){
////        	tokenService.changeUserAppToken(user.getId());
////        }
////    }
////
////    @Override
////    public void updateUserNoSynAddressbook(User user) {
////        User u = userRepository.findNoCache(user.getId());
////
////        saveUser(user,false);
////
////        if (u != null && user.getName() != null && !user.getName().equals(u.getName())) {
////            userNetworkService.updateName(user.getId(), user.getName());
////        }
////    }
////
////    /**
////     * 为了防止帐号与密码被覆盖,全部使用本方法保存用户对象. 针对修改密码或邮箱的场景,另外提供方法
////     * @param user
////     * @return
////     */
////	private boolean saveUser(User user, boolean createNew) {
////		//手机注册时，email为空，寻找existUser会报错。
////        User existUser = null;
////        if(createNew==false){
////        		existUser = this.get(user.getId());
////        		if(existUser ==null){
////        			existUser = this.findByEmail(user.getEmail());
////        		}
////	        if(existUser == null && user.isValidMobile()){
////	        	   existUser = this.findByMobilePhone(user.getMobile());
////	        }
////        }
////
////
////        if (existUser != null) {
////            user.setId(existUser.getId());
////            
////            if (existUser.extended() != null) {
////                user.putAll(existUser.extended());
////            }
////            if (existUser.getRegisterDate() != null)
////                user.setRegisterDate(existUser.getRegisterDate());
////            
////          
////            //lian_lin 2014-03-07解决公共邮箱解绑时，email需要强制置空的
////            if(StringUtils.isEmpty(user.getEmail())&&!user.isUnBindAccount()){
////            	user.setEmail(existUser.getEmail());
////            }
////            
////            //lian_lin 2014-03-07解决未知地方，把mobile置空了，引起mobile手机丢失。
////            if(StringUtils.isEmpty(user.getMobile())){
////            	user.setMobile(existUser.getMobile());
////            }else if(" ".equals(user.getMobile())){
////            	//lian_lin 2014-07-11 这个表示业务要求强制置空的 ，由于以上防手机丢失造成手机置空不了。所以出此下策。
////            	user.setMobile("");
////            }
////            user.setPassword(existUser.getPassword());
////            
////        } else {
////            String id = user.getId();
////            if (id == null || id.length() < 20) {
////                id = ObjectId.get().toString();
////                user.setId(id);
////            }
////            user.setPassword(PasswordHelper.hashPassword(id, user.getPassword()));
////          
////        }
////        
////        userRepository.save(user);
////        //lian_lin 2014-03-07解决公共邮箱解绑时，需要强制清除缓存。
////        if(existUser != null&&user.isUnBindAccount()){
////        	User tmpUser = new User();
////        	tmpUser.setId(existUser.getId());
////        	tmpUser.setEmail(existUser.getEmail());
////        	this.userRepository.removeCache(tmpUser);
////        }
////        
////        
////        try{
//////        	long a = System.currentTimeMillis();
////        	//不同步到云平台了
//////        	this.platformAccountService.synUser2CMCloud(user);
//////        	long b = System.currentTimeMillis();
//////        	logger.info("saveUser_synUser2CMCloud runtime:"+(b-a));
////        } catch (Exception e) {
//////        	logger.error("同步到云平台报错:"+e);
////        	logger.error("同步到云平台报错:"+e.getMessage());
////        }
////        
////        
////        return existUser!=null;
////	}
////
////    @Override
////    public void updateDefaultNetwork(String userId) {
////        User user = get(userId);
////        String defaultNetwork = null;
////        if (user != null) {
////            List<Network> networks = userNetworkService.getUserNetworks(userId);
////            switch (networks.size()) {
////            case 0:// 无网络
////                break;
////            case 1:// 只有一个时，则为默认网络
////                defaultNetwork = networks.get(0).getId();
////                break;
////            default://
////                Map<String, Network> companies = new HashMap<String, Network>(networks.size());
////                Map<String, Network> communities = new HashMap<String, Network>(networks.size());
////                for (Network n : networks) {
////                    if (NetworkType.COMPANY.equals(n.getType())) {
////                        companies.put(n.getId(), n);
////                    } else if (NetworkType.COMMUNITY.equals(n.getType())) {
////                        communities.put(n.getId(), n);
////                    }
////                }
////                if (companies.size() > 0) {
////                    if (user.getDefaultNetwork() != null && companies.containsKey(user.getDefaultNetwork())) {
////                        defaultNetwork = user.getDefaultNetwork();
////                    } else {
////                        defaultNetwork = companies.keySet().iterator().next();
////                    }
////                } else if (communities.size() > 0) {
////                    if (user.getDefaultNetwork() != null && communities.containsKey(user.getDefaultNetwork())) {
////                        defaultNetwork = user.getDefaultNetwork();
////                    } else {
////                        defaultNetwork = communities.keySet().iterator().next();
////                    }
////                }
////            }
////            if (defaultNetwork != null && !defaultNetwork.equals(user.getDefaultNetwork())) {
////                user.setDefaultNetwork(defaultNetwork);
////                modifyUser(user);
////                //sendXTAboutUser(user, ToxtConsumerType.TOXT_UPDATE_USER);
////            }
////        }
////    }
////
////    /*
////     * (non-Javadoc)
////     * 
////     * @see com.kingdee.cbos.user.UserService#automaticallyCreateUser(java.lang.String)
////     */
////    @Override
////    public User ensureUser(String email) {
////        if (StringUtils.isEmpty(email)) {
////            return null;
////        } else {
////            email = email.trim();
////        }
////
////        String emailDomain = domainRelationService.getDomainByEmail(email);
////        boolean exist = this.espService.exist(emailDomain);
////        if (exist) {
////            //公共邮箱不自动创建用户
////            return null;
////        }
////
////        String emailLocalPart = EmailUtils.getEmailLocalPart(email);
////        Company company = this.createCompany(emailDomain);
////
////        User user = this.findByEmail(email);
////        if (user == null || UserStatus.UNVERIFIED.equals(user.getStatus())) {// 可能存在未激活用户
////            user = new User();
////            user.setEmail(email);
////            user.setCorpName(company.getName());
////            user.setName(emailLocalPart);
////            user.setVerifiedEmail(true);
////            user.setPublicMailBox(false);
////            user.setRegisterDate(new Date());
////            user.setResetPwd(true);
////            user.setValidMobile(false);
////            user.setDefaultNetwork(company.getId());
////
////            RegistrationRecord registrationRecord = new RegistrationRecord();
////            registrationRecord.setApplication("Zhihui");
////            registrationRecord.setOriginated(true);
////            registrationRecord.setRegistrationTime(new Date());
////            user.addRegistrationRecord(registrationRecord);
////
////            user = this.createUser(user);
////        } else {
////            boolean registeredWithZhihui = false;
////            List<RegistrationRecord> registrationRecords = user.getRegistrationRecords();
////            for (RegistrationRecord registrationRecord : registrationRecords) {
////                if ("Zhihui".equals(registrationRecord.getApplication())) {
////                    registeredWithZhihui = true;
////                }
////            }
////
////            if (!registeredWithZhihui) {
////                RegistrationRecord registrationRecord = new RegistrationRecord();
////                registrationRecord.setApplication("Zhihui");
////                registrationRecord.setOriginated(registrationRecords.isEmpty());
////                registrationRecord.setRegistrationTime(new Date());
////                user.addRegistrationRecord(registrationRecord);
////            }
////            
////            userRepository.save(user);
////           
////        }
////
////        this.userNetworkService.addUser(user.getId(), company.getId());
////        SnsUser su = snsUserService.getSnsUser(company.getId(), user.getId());
////        if (su == null) {
////            this.snsUserService.createSnsUser(user.getId(), company.getId());
////        } else if (MemberStatus.NOT_ACTIVE.toString().equals(su.getForbidStatus())) {
////            snsUserService.updateForbidStatus(company.getId(), user.getId(), MemberStatus.ACTIVE.toString());
////        }
////
////        return user;
////    }
////
////    private Company createCompany(String domain) {
////        if (StringUtils.isEmpty(domain)) {
////            String msg = "\"domain\" can't be empty!";
////            throw new IllegalArgumentException(msg);
////        } else {
////            domain = domain.trim();
////        }
////
////        Domain domainObj = this.createDomain(domain);
////        Company company = this.networkRepository.getCompany(domain);
////        if (company == null) {
////            company = new Company();
////            company.setName(domainObj.getIdentity());
////            company.setDomainId(domainObj.getId());
////            company.setDomainIdentity(domainObj.getIdentity());
////            company.setSubDomainName(domainObj.getIdentity());
////            company.setRegisterDate(new Date());
////            this.networkRepository.storeCompany(company);
////            this.quotaDAO.initMaxQuota(company.getId());
////        }
////
////        return company;
////    }
////
////    private Domain createDomain(String domain) {
////        if (StringUtils.isEmpty(domain)) {
////            String msg = "\"domain\" can't be empty!";
////            throw new IllegalArgumentException(msg);
////        } else {
////            domain = domain.trim();
////        }
////
////        Domain domainObj = this.domainRepository.findByIdentity(domain);
////        if (domainObj == null) {
////            domainObj = new Domain();
////            domainObj.setIdentity(domain);
////            domainObj.setName(domain);
////            this.domainRepository.save(domainObj);
////        }
////
////        return domainObj;
////    }
////
////    @Override
////    public boolean isFristActiveCompanyUser(String userId, Date fromDate) {
////        User user = this.get(userId);
////        if (user == null || user.isPublicMailBox() || !UserStatus.APPLY.equals(user.getStatus())
////                || user.getRegisterDate() == null || (fromDate != null && user.getRegisterDate().before(fromDate)))
////            return false;
////
////        Network network = networkService.get(user.getDefaultNetwork());
////        if (network != null && userId.equals(network.getCreatorId()))
////            return true;
////
////        return false;
////    }
////    
////    /**
////     * @author lian_lin 主要判断此用户是否已加入开发社区
////     * @param userId 用户id
////     * @return 返回 User对象中的 isDevelog的值,如果为null,设置为 false
////     */
////    @Override
////    public boolean isDevelop(String userId){
////    	boolean flag = false;
////    	if(StringUtils.isBlank(userId)){
////    		return flag;
////    	}
////    	User user = this.get(userId);
////    	if(user==null){
////    		return flag;
////    	}
////    	if(user.isDevelop())
////    		flag = true;
////    	return flag;
////    }
////    
////    /**
////     * @author lian_lin 加入开发社区
////     * @param userId 用户id
////     * @return 
////     */
////    @Override
////    public void joinDevelop(String userId){
////    	if(StringUtils.isBlank(userId)){
////    		return;
////    	}
////    	User user = this.get(userId);
////    	if(user==null){
////    		return;
////    	}
////    	user.setDevelop(true);
////    	this.updateUser(user);
////    
////    }
////    
////    /**
////     * @author lian_lin 取消开发社区
////     * @param userId 用户id
////     * @return 
////     */
////    @Override
////    public void cancelDevelop(String userId){
////    	if(StringUtils.isBlank(userId)){
////    		return;
////    	}
////    	User user = this.get(userId);
////    	if(user==null){
////    		return;
////    	}
////    	user.setDevelop(false);
////    	this.updateUser(user);
////    }
////    
////    @Override
////    public long countByDefaultNetworkAndStatus(String defaultNetwork,String status)
////    {
////        return userRepository.countByDefaultNetworkAndStatus(defaultNetwork, status);
////    }
////    
////    @Override
////    public List<User> getByDefaultNetworkAndStatus(String defaultNetwork,String status,int start, int limit)
////    {
////        return userRepository.fingByDefaultNetworkAndStatus(defaultNetwork, status, start, limit);
////    }
////    
////    
////    /**
////     * @since 2013-11-25 运营要求，直接给手机用户生成不带网络的用户
////     * @author lian_lin
////     * @param mobile
////     * @param passowrd
////     * @return
////     */
////    @Override
////    public Map<String,String> createUserOfMobile(String mobile,String password){
////    	Map<String,String> result = new HashMap<String, String>();
////    	if(StringUtils.isBlank(mobile)
////    			||StringUtils.isBlank(password)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "mobile,password is empty!");
////    	    return result;
////    	}
////    	
////    	if(!MobileUtil.isMobile(mobile)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "mobile is not mobile!");
////    		return result;
////    	}
////    	
////    	if(accountService.isRegAccount(mobile)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "mobile is register!");
////    		return result;
////    	}
////    	try{
////	    	User user = new User();
////	        String id = ObjectId.get().toString();
////	        user.setId(id);
////	    	user.setPublicMailBox(true);
////	    	user.setEmail(null);
////	    	user.setVerifiedEmail(false);
////	    	user.setMobile(mobile);
////	    	user.setValidMobile(true);
////	    	user.setRegisterDate(new Date());
////	    	user.setModifyDate(new Date());
////	    	user.setUserType(UserType.COMMON);
////	        user.setName(mobile);
//////	        String hashed = PasswordHelper.hashPassword(user.getId(), user.getPassword());
////	        user.setPassword(password);
////	        this.createUser(user);
//////	        userRepository.save(user);
//////	        this.accountService.bindAccount(AccountType.MOBILE, mobile, user.getId());
//////	        this.accountService.bindAccount(AccountType.KDWEIBO, String.valueOf(user.getUserSequence()), user.getId());
////	        result.put("success", Boolean.TRUE.toString());
////	        result.put("userId", id);
////    	}catch(Exception e){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", e.getMessage());
////    	}
////        return result;
////    }
////    
////    
////    
////    /**
////     * @since  更新指定用户未处理邀请数
////     * @author lian_lin
////     * @param userId 
////     * @param unreadInviteCount 未处理邀请数
////     */
////    @Override
////    public void updateUnreadInviteCount(String userId,int unreadInviteCount){
////    	this.userRepository.updateUnreadInviteCount(userId, unreadInviteCount);
////    }
////    
////    
////    /**
////     * @since 2014-02-21 第三方授权系统直接生成关联用户
////     * @author lian_lin
////     * @param accountType 账户类型
////     * @param openId 第三方系统唯一标识符
////     * @param info 第三方额外信息
////     * @return
////     */
////    @Override
////    public Map<String,String> createUserOfThirdParty (AccountType accountType,String openId,Map<String,Object> info){
////    	Map<String,String> result = new HashMap<String, String>();
////    	if(accountType==null){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "accountType is empty!");
////    		return result;
////    	}
////    	
////    	if(StringUtils.isBlank(openId)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "openId is empty!");
////    		return result;
////    	}
////
////    	try{
////	    	User user = new User();
////	        String id = ObjectId.get().toString();
////	        user.setId(id);
////	    	user.setPublicMailBox(false);
////	    	user.setEmail(null);
////	    	user.setVerifiedEmail(false);
////	    	user.setMobile(null);
////	    	user.setValidMobile(false);
////	    	user.setRegisterDate(new Date());
////	    	user.setModifyDate(new Date());
////	    	user.setUserType(UserType.COMMON);
////	        user.setName(info==null?String.valueOf(user.getUserSequence()):(String)info.get("name"));
////	        user.setPhotoId(info==null?"":(String)info.get("photoId"));
////	        user.setPassword(null);
//////	        if(info.get("bindUserInfo")!=null){
//////	        	user.setBindUserInfo((BindUserInfo)info.get("bindUserInfo"));
//////	        }
////	        user.setResetPwd(false);
////	        userRepository.save(user);
////	        this.accountService.bindAccount(accountType, openId, user.getId());
////	        this.accountService.bindAccount(AccountType.KDWEIBO, String.valueOf(user.getUserSequence()), user.getId());
////	        result.put("success", Boolean.TRUE.toString());
////	        result.put("userId", id);
////    	}catch(Exception e){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", e.getMessage());
////    	}
////        return result;
////    }
////    
////    /**
////     * @since 2014-02-21 用戶申请绑定账户相关业务逻辑
////     * @author lian_lin
////     * @param userId 用户ID
////     * @param accountType 賬戶類型
////     * @param account 賬戶名稱
////     * @param password 账户密码。如果已设置密码，可以为空
////     * @return
////     */
////    @Override
////    public Map<String,String> preAccountBind (String userId,AccountType accountType,String account,String password){
////    	Map<String,String> result = new HashMap<String, String>();
////    	if(accountType==null){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "accountType is empty!");
////    		return result;
////    	}
////    	
////    	if(StringUtils.isBlank(userId)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "userId is not empty!");
////    		return result;
////    	}
////    	if(StringUtils.isBlank(account)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "account is not empty!");
////    		return result;
////    	}
////    	try{
////    		User user = this.get(userId);
////    		if(user==null){
////    			result.put("success", Boolean.FALSE.toString());
////        		result.put("errorMsg", "该用户不存在!");
////        		return result;
////    		}
////    		
//////    		if(this.needSetPassworkd(userId)
//////    				&&StringUtils.isEmpty(password)){
//////    			result.put("success", Boolean.FALSE.toString());
//////        		result.put("errorMsg", "请设置密码!");
//////        		return result;
//////    		}
////    		
//////    		if((AccountType.EMAIL.equals(accountType)&&user.isVerifiedEmail())
//////    				||(AccountType.PUBLICEMAIL.equals(accountType)&&user.isVerifiedPublicEmail())){
//////    			result.put("success", Boolean.FALSE.toString());
//////        		result.put("errorMsg", "用户已绑定邮箱!");
//////        		return result;
//////    		}
////    		boolean isReg = true;
////            if(this.accountService.isRegAccount(account)){
////            	//判断被人家抢注了，但是邮箱没有被验证过的。
////            	if(AccountType.PUBLICEMAIL.equals(accountType)&&!user.isVerifiedPublicEmail()){
////            		isReg = false;
////            	}
////            	if(isReg){
////	            	result.put("success", Boolean.FALSE.toString());
////	        		result.put("errorMsg", "账户已被注册!");
////	        		return result;
////            	}
////            }
////            this.accountBindService.saveOfMail(account, password,user);
////    	}catch(Exception e){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", e.getMessage());
////    	}
////    	result.put("success", Boolean.TRUE.toString());
////        return result;
////    }
////    
////    /**
////     * @since 2014-02-21 用戶綁定賬戶
////     * @author lian_lin
////     * @param userId 用户ID
////     * @param accountType 賬戶類型
////     * @param account 賬戶名稱
////     * @param password 账户设置密码
////     * @return
////     */
////    @Override
////    public Map<String,String> accountBind (String userId,AccountType accountType,String account,String password){
////    	Map<String,String> result = new HashMap<String, String>();
////    	if(accountType==null){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "accountType,password is empty!");
////    		return result;
////    	}
////    	
////    	if(StringUtils.isBlank(userId)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "userId is not empty!");
////    		return result;
////    	}
////    	
////    	if(StringUtils.isBlank(account)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "account is not empty!");
////    		return result;
////    	}
////
////    	try{
////	    	User user = this.get(userId);
////	    	if(AccountType.EMAIL==accountType){
////	    		//當企業郵箱綁定時，存放在email的公共郵箱存放在publicMail郵箱中
////	    		if(!StringUtils.isBlank(user.getEmail())
////	    				&&StringUtils.isBlank(user.getPublicEmail())){
////	    			user.setPublicEmail(user.getEmail());
////	    		}
////	    		user.setEmail(account);
////	    		user.setVerifiedEmail(true);
////	    	}else if(AccountType.PUBLICEMAIL==accountType){
////	    		if(user.isVerifiedEmail()){
////	    			user.setPublicEmail(account);
////	    		}else{
////	    			user.setEmail(account);
////	    		}
////	    		user.setVerifiedPublicEmail(true);
////	    		user.setPublicMailBox(true);
////	    		
////	    		//删除被别人抢注的公共邮箱RETRIEVE
////	    		Account retrieveAccount = this.accountService.getAccountByName(account);
////	    		if(retrieveAccount!=null){
////	    			this.accountService.deleteAccount(retrieveAccount);
////	    			this.accountLogService.retrieveAccount(retrieveAccount, user, user.getId());
////	    			User retrieveUser = this.get(retrieveAccount.getUser());
////	    			if(account.equals(retrieveUser.getPublicEmail())){
////	    				retrieveUser.setPublicEmail("");
////	    			}
////                    if(account.equals(retrieveUser.getEmail())){
////	    				retrieveUser.setEmail("");
////	    			}
////                    userRepository.save(retrieveUser);
////	    		}
////	    		
////	    	}else if(AccountType.MOBILE==accountType){
////	    		user.setMobile(account);
////	    		user.setValidMobile(true);
////	    	}else{
////	    		result.put("success", Boolean.FALSE.toString());
////	    		result.put("errorMsg", "类型不匹配!");
////	    		return result;
////	    	}
////	    	//2014-03-31 又加了一个没有设置密码，在绑定账户时需要设置密码。坑爹的很.，又变成只有手机绑定才个设置密码
////	    	if(AccountType.MOBILE==accountType
////	    			&&this.needSetPassword(user.getId())
////	    			&&StringUtils.isNotEmpty(password)){
////	            // 修改密码时，对其密码进行哈希转换
////	            String hashed = PasswordHelper.hashPassword(user.getId(), password);
////	            user.setPassword(hashed);
////	            //设置是否重置密码为false，管理员在设置别人密码是，应注意是否需要提示用户设置密码
////	            user.setResetPwd(false);
////	    	}
////	    	
////	        userRepository.save(user);
////	        sendXTAboutUser(user, ToxtConsumerType.TOXT_UPDATE_USER);
////	       
////	        this.accountService.bindAccount(accountType, account, user.getId());
////	        if(AccountType.EMAIL==accountType){
////	        	this.userNetworkService.addUserOfBindEmail(userId, account);
////	        }
////	        result.put("success", Boolean.TRUE.toString());
////    	}catch(Exception e){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", e.getMessage());
////    	}
////        return result;
////    }
////    
////    /**
////     * @since 账号解绑
////     * @param userId
////     * @param account
////     * @return
////     */
////    @Override
////    public Map<String,String> accountUnBind(String userId,String account){
////    	Map<String,String> result = new HashMap<String, String>();
////    	if(StringUtils.isBlank(userId)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "userId is not empty!");
////    		return result;
////    	}
////    	if(StringUtils.isBlank(account)){
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "account is not empty!");
////    		return result;
////    	}
////    	
////    	try{
////	    	User user = this.get(userId);
////	    	if(user==null){
////	    		logger.info("accountUnBind user is null");
////	    		result.put("success", Boolean.FALSE.toString());
////	    		result.put("errorMsg", "用户不存在!");
////	    		return result;
////	    	}
////	    	
////	    	if(account.equals(user.getMobile())){
////	    		user.setMobile(" ");//防止空值，手机被弄回去
////	    		user.setValidMobile(false);
////	    		this.modifyUser(user);
////	    	}
////	    	if(account.equals(user.getPublicEmail())){
////	    		user.setPublicEmail("");
////	    		user.setVerifiedPublicEmail(false);
////	    		this.modifyUser(user);
////	    	}
////	    	
////	    	if(account.equals(user.getEmail())
////	    			&&user.isVerifiedPublicEmail()
////	    			&&espService.isPublicEmail(account)){
////	    		user.setEmail("");
////	    		user.setPublicEmail("");
////	    		user.setVerifiedPublicEmail(false);
////	    		this.modifyUser(user);
////	    	}
////	    	
////	    	Account account1 = this.accountService.getAccountByName(account);
////	    	if(account1==null){
////	    		result.put("success", Boolean.FALSE.toString());
////	    		result.put("errorMsg", "account is not exist!");
////	    		return result;
////	    	}
////	    	if(!account1.getUser().equals(userId)){
////	    		logger.info("accountUnBind  account.getUser not equal userId");
////	    		result.put("success", Boolean.FALSE.toString());
////	    		result.put("errorMsg", "非法解绑！");
////	    		return result;
////	    	}
////	    	result.put("success", Boolean.TRUE.toString());
////	    	this.accountService.deleteAccount(account1);
////	    	this.accountBindService.cancelAccountBind(userId, account);
////	    	
////	    	
////    	}catch(Exception e){
////    		logger.error("accountUnBind is error account: "+account+" "+e.getMessage(),e);
////    		result.put("success", Boolean.FALSE.toString());
////    		result.put("errorMsg", "后台未知异常，请联系管理员！");
////    	}
////    	return result;
////    }
////    
////    
////    
////    
////    /**
////     * @since  2014-02-28 获取用户账户绑定信息
////     * @author lian_lin
////     * @param userId 
////     */
////    @Override
////    public List<AccountBindVO> findBindAccount(String userId){
////    	List<AccountBindVO> accountBindVOs = new ArrayList<AccountBindVO>();
////    	List<Account> accounts = this.accountService.findAccount(userId);
////    	
//////    	Set<String> set = new HashSet<String>();
//////    	for(AccountBind accountBind:accountBinds){
//////    		set.add(accountBind.getAccount());
//////    	}
////    	//
////        Set <String> accountTypes = new HashSet<String>();
////        accountTypes.add(AccountType.EMAIL.toString());
////        accountTypes.add(AccountType.PUBLICEMAIL.toString());
////        accountTypes.add(AccountType.MOBILE.toString());
////        
////       String accountType = null;
////       
////    	for(Account account:accounts){
////    		accountType = account.getAccountType().toString();
////    		if(!AccountType.KDWEIBO.toString().equals(accountType)){
////    			AccountBindVO accountBindVO = new AccountBindVO();
////    			accountBindVO.setAccountName(account.getName());
////    			accountBindVO.setAccountType(accountType.toString());
////    			accountBindVO.setStatus("BINDED");
////    			//未验证公共邮箱属于，归到绑定待验证中
////    			if(AccountType.PUBLICEMAIL.toString().equals(accountType.toString())){
////        			User user = this.get(account.getUser());
////        			if(!user.isVerifiedPublicEmail()){
////        				accountBindVO.setStatus("BINDING");
////        			}
////    			}
////    			if(accountTypes.contains(accountType)){
////    				accountTypes.remove(accountType);
////    			}
////    			accountBindVOs.add(accountBindVO);
////    		}
////    	}
////    	
////    	List<AccountBind> accountBinds = this.accountBindService.find(userId, AccountBindStatus.BINDING);
////    	for(String unBindAccountType:accountTypes){
////    		AccountBindVO accountBindVO = new AccountBindVO();
////    		accountBindVO.setAccountName("");
////    		accountBindVO.setStatus("UN_BIND");
////    		if(!AccountType.MOBILE.toString().equals(unBindAccountType)){
////			    for(AccountBind accountBind:accountBinds){
////			    	if(unBindAccountType.equals(accountBind.getType().toString())){
////			    		accountBindVO.setAccountName(accountBind.getAccount());
////			    		accountBindVO.setStatus("BINDING");
////			    		break;
////			    	}
////			    }
////    		}
////    		accountBindVO.setAccountType(unBindAccountType);
////    		accountBindVOs.add(accountBindVO);
////    	}
////    	return accountBindVOs;
////    }
////
////	@Override
////	public void updateUnreadDisbandNetworkCount(String userId,
////			int unreadDisbandNetworkCount) {
////		this.userRepository.updateUnreadDisbandNetworkCount(userId, unreadDisbandNetworkCount);
////	}
////    
////	/**
////	 * @since 2014-03-13 未读邀请条数，工作圈解散的条数可能不一致。在我的云之家首页的检测一下发现不一致。就重置一下
////	 * @author lian_lin
////	 * @param userId 
////	 * @param disbandNetworkCount 
////	 * @param inviteCount
////	 */
////	@Override
////	public void updateUnreadCount(String userId,int inviteCount,int disbandNetworkCount){
////		if(StringUtils.isEmpty(userId)){
////			return;
////		}
////		User user = this.get(userId);
////		if(user==null){
////			return;
////		}
////		if(inviteCount!=user.getUnreadInviteCount()){
////			this.updateUnreadInviteCount(userId, inviteCount);
////		}
////		if(disbandNetworkCount!=user.getUnreadDisbandNetworkCount()){
////			this.updateUnreadDisbandNetworkCount(userId, disbandNetworkCount);
////		}
////	}
////	
////	/**
////	 * @since 2014-03-31 判断指定用户是否需要设置密码
////	 * @author lian_lin
////	 * @param userId
////	 * @return 密码为空时，并且重置密码标识为true 返回true，否则返回false
////	 */
////	@Override
////	public boolean needSetPassword(String userId){
////		boolean flag = false;
////		if(StringUtils.isEmpty(userId)){
////			return flag;
////		}
////		User user = this.get(userId);
////		if(user==null){
////			return flag;
////		}
//////		if(StringUtils.isEmpty(user.getPassword())
//////				&&user.isResetPwd()){
////		if(StringUtils.isEmpty(user.getPassword())){
////			flag = true;
////		}
////		return flag;
////	}
////	
////	/**
////	 * @since 2014-04-01 判断指定用户能否设置密码
////	 * @author lian_lin
////	 * @param userId
////	 * @return 用户里面不包含邮件和手机账号，就返回false，否则返回true
////	 */
////	@Override
////	public boolean canSetPassword(String userId){
////		return this.noEmailAndMobile(userId)?false:true;
////	}
////	
////	
////	/**
////	 * @since 2014-04-03 判断指定用户没有绑定邮箱及手机
////	 * @author lian_lin
////	 * @param userId
////	 * @return 用户里面包含邮件或者手机账号，就返回false，否则返回true
////	 */
////	@Override
////	public boolean noEmailAndMobile(String userId){
////		boolean flag = true;
////		List<Account> accounts = this.accountService.findAccount(userId);
////		String accountType;
////		for(Account account: accounts){
////			accountType = account.getAccountType().toString();
////			if(AccountType.EMAIL.toString().equals(accountType)
////					||AccountType.PUBLICEMAIL.toString().equals(accountType)
////					||AccountType.MOBILE.toString().equals(accountType)){
////				flag = false;
////				break;
////			}
////		}
////		return flag;
////	}
////	
////	/**
////	 * @since 2014-04-03 判断指定只有第三方账户，并返回账户类型
////	 * @author lian_lin
////	 * @param userId
////	 * @return 用户里面不包含邮件和手机账号，返回第三方账户系统类型的。
////	 */
////	@Override
////	public String getOnlyOpenAccountType(String userId){
////		String result = "";
////		boolean flag = false;
////		List<Account> accounts = this.accountService.findAccount(userId);
////		String accountType;
////		for(Account account: accounts){
////			accountType = account.getAccountType().toString();
////			if(AccountType.EMAIL.toString().equals(accountType)
////					||AccountType.PUBLICEMAIL.toString().equals(accountType)
////					||AccountType.MOBILE.toString().equals(accountType)){
////				flag = true;
////				break;
////			}
////		}
////		if(!flag){
////			for(Account account: accounts){
////				accountType = account.getAccountType().toString();
////				if(!AccountType.EMAIL.toString().equals(accountType)
////						&&!AccountType.PUBLICEMAIL.toString().equals(accountType)
////						&&!AccountType.MOBILE.toString().equals(accountType)
////						&&!AccountType.KDWEIBO.toString().equals(accountType)){
////					result = accountType;
////					break;
////				}
////			}	
////		}
////		return result;
////	}
////
////	@Override
////	public long countUsers(UserStatus userStatus, boolean excludeTeamUser) {
////		
////		return this.userRepository.countUsers(userStatus, excludeTeamUser);
////	}
////
////	@Override
////	public void modifyUserSourceXT(User user) {
////		if(null == user) {
////			return ;
////		}
////		
////		User u = this.userRepository.find(user.getId());
////		if(null == u) {
////			logger.info("XT to weibo user is not exists! userId:"+user.getId());
////			return ;
////		}
////		
////		if(!StringUtils.isEmpty(user.getName())) {
////			u.setName(user.getName());
////		}
////		if(!StringUtils.isEmpty(user.getRealName())) {
////			u.setRealName(user.getRealName());
////		}
////		
////		if(!StringUtils.isEmpty(user.getJobTitle())) {
////			u.setJobTitle(user.getJobTitle());
////		}
////		
////		if(!StringUtils.isEmpty(user.getWorkMail())) {
////			u.setWorkMail(user.getWorkMail());
////		}
////		
////		if(!StringUtils.isEmpty(user.getWorkMobileExt())) {
////            u.setWorkMobileExt(user.getWorkMobileExt());
////        }
////		
////		if(user.getWorkPhone()!=null) {
//////			if(!StringUtils.isEmpty(user.getWorkPhone())) {
//////			u.setWorkMobile(user.getWorkPhone());
////			u.setWorkPhone(user.getWorkPhone());//这里把讯通的办公电话同步到座机字段上
////		}
////		if(user.getWorkPhone2()!=null) {
////			u.setWorkPhone2(user.getWorkPhone2());//这里把讯通的办公电话2同步到座机字段上
////		}		
////		
////		if(user.getBirthday() != null) {
////			u.setBirthday(user.getBirthday());
////		}	
////		
////		if(!StringUtils.isEmpty(user.getBirthDay_str())) {
////			u.setBirthDay_str(user.getBirthDay_str());
////		}
////		
////		if(user.getGender() != null) {
////            u.setGender(user.getGender());//性别
////        }
////		
////		if(!StringUtils.isEmpty(user.getWorkMobileExt())) {
////			u.setWorkPhoneExt(user.getWorkPhoneExt());
////		}
//////		if(!StringUtils.isEmpty(user.getWorkMailExt())) {//同步邮箱
////		if(user.getWorkMailExt()!=null) {//同步邮箱	
////			u.setWorkMailExt(user.getWorkMailExt());
////		}
////		if(user.getStatus()!=null){
////			u.setStatus(user.getStatus());
////		}
////		
////		boolean ischangePwd = false;
////		if(!StringUtils.isEmpty(user.getPassword())) {
////			if(!u.getPassword().equals(user.getPassword())) {
////				ischangePwd = true;
////				u.setPassword(user.getPassword());
////				u.setXtPassword(user.getPassword());
////				accountService.syncUserToAccount(u.getId());
////			}
////		}
////		//无部门的情况也要包含在内
////		if(null != user.getDepartment()) {
////			u.setDepartment(user.getDepartment());
////		}
////		
////		if(!StringUtils.isEmpty(user.getJobTitle())) {
////			u.setJobTitle(user.getJobTitle());
////		}
////		u.setModifyDate(new Date());
////		u.setPublishContact(user.isPublishContact());//增加是否显示隐藏手机号的标识
////		this.userRepository.save(u);
////		userNetworkService.updateName(u.getId(), u.getName());
////	    //先不做这个，因为这次没考虑
////		//addressBookService.synchronize(u);
////		
////		//修改密码后更新用户各端token
////		if(ischangePwd){
////			tokenService.changeUserAppToken(u.getId());
////		}
////	}
////
////	@Override
////	public long countUsersForXT(UserStatus userStatus) {
////		
////		return this.userRepository.countUsersForXT(userStatus);
////	}
////
////	@Override
////	public List<User> getUsersForXT(UserStatus userStatus, int start, int limit) {
////		
////		return this.userRepository.getUsersForXT(userStatus, start, limit);
////	}
////	
////	/**
////     * @since 只用于讯通用户同步过来的新增用户，云之家业务不能调用
////     * @param user
////     * @return
////     */
////    @Override
////    public User createUserSourceXt(User user) {
////    	if(null == user || StringUtils.isEmpty(user.getId())) {
////    		logger.info("XT to weibo addUser user is null");
////    		return null;
////    	}
////    	
////    	 user.setStatus(UserStatus.APPLY);
////    	 
////    	User u = this.userRepository.find(user.getId());
////    	
////    	if( null == u && !StringUtils.isEmpty(user.getMobile()) ) {
////    		u = this.userRepository.findByMobilePhone(user.getMobile());
////    		if(null != u && !u.isValidMobile()) {
////    			u = null;
////    		}
////    	}
////    	
////    	//发现在查找的时候没有加邮箱是否验证的判断
////    	if(null == u && !StringUtils.isEmpty(user.getEmail())) {
////    		u = this.userRepository.findByEmail(user.getEmail());
////    		if(null != u && !u.isVerifiedEmail()) {
////    			u = null;
////    		}
////    	}
////    	
////    	if(null != u && UserStatus.APPLY.equals(u.getStatus())) {
////    		
////    		logger.info("XT to weibo user 已经存在-- id:"+user.getId());
////        	throw new RuntimeException("用户已经存在");
////    	} else {
////    		user.setRegSource("XT");
////    		user.setDefaultNetwork(Network.PUBLIC_VIRTUAL_ID);
////    		if (saveUser(user,true)) {
////                userStatService.addUser(user);
////            }
////
////             this.createUser(user);
////           //lian_lin 2013-11-06 公共邮箱及手机主动注册，创建用户并没有defaultNetworkID，
////             //但是synchronizeNewUser必须得有defaultNetworkID.因此如果没有defaultNetworkID时，不执行以下业务
////             
////             user = this.get(user.getId());
////    	     int count = 1;
////    	     while (null == user && count < 4) {
////    	    	 try {
////    	    		 logger.info("XT to weibo 创建用户等待"+count+"次");
////    				Thread.sleep(200 * count);
////    				user = this.get(user.getId());
////    				count++;
////    			} catch (InterruptedException e) {
////    				logger.info("XT to weibo 创建用户等待"+count+"次出错",e);
////    			}
////    	     }
////             return user;
////    	}
////    	    	
////    }
////    
////    /**
////     * @since 2014-05-20 判断是否为金蝶管理员账号.此账号需要做些比较大的权限操作
////     * @author lian_lin
////     * @param session
////     * @return
////     */
////    @Override
////    public boolean isKingdeeAdmin(Session session){
////    	boolean flag = false;
////    	String userId = session.getUserId();
////    	String networkId = session.getNetworkId();
////    	if(!"383cee68-cea3-4818-87ae-24fb46e081b1".equals(networkId)){
////    		return flag;
////    	}
////        UserNetwork un = userNetworkService.getUserNetwork(userId, networkId);
////         if(un!=null) {
////             UserRole role =  userNetworkService.getUserRole(userId, networkId);
////             if(role!=null&&role.isAdmin()){
////            	 flag = true;
////             }
////         }
////        return flag;
////    }
////
////    /**
////     * @since 2014-6-13 由于讯通那边数据出乱子，一个人出现了多个openID,多个openID关联云之家同一个userID,
////     * 所以领导们讨论决定，要在云之家这边容错，通过openID获取云之家用户 
////     * 所以限用于移动端，讯通过来获取token做的容错处理
////     * @author lian_lin
////     * @param xtId
////     * @return
////     */
////    @Override
////	public User getUserFromXt(String xtId) {
////		if(StringUtils.isEmpty(xtId)) {
////			return null;
////		}
////		User user = this.get(xtId);
////		if(user==null
////				||(!UserStatus.APPLY.name().equals(user.getStatus().name()))){
////			user = this.userRepository.getUserByXtId(xtId);
////		}
////		return user;
////	}
////    
////	@Override
////	public User getUserByXtId(String xtId) {
////		if(StringUtils.isEmpty(xtId)) {
////			return null;
////		}
////		return this.userRepository.getUserByXtId(xtId);
////	}
////	
////	/**
////	 * @since 2014-06-30 给讯通用户调用，重置手机号码
////	 * @author lian_lin
////	 * @param account
////	 * @param userId
////	 * @return
////	 */
////	@Override
////	public Map<String,String> xtResetMobileAccount(String account,String userId){
////		Map<String,String> result = new HashMap<String, String>();
////	   if(StringUtils.isEmpty(account)
////			   ||StringUtils.isEmpty(userId)){
////		   result.put("status", Boolean.FALSE.toString());
////		   result.put("errorMsg","account userId can not empty");
////		   return result;
////	   }
////	   //支持非手机账号的更新，所以屏蔽下面的处理
//////	   if(!MobileUtil.isMobile(account)){
//////		   result.put("status", Boolean.FALSE.toString());
//////		   result.put("errorMsg","this account type is not mobile");
//////		   return result; 
//////	   }
////	   
////	   User user = this.get(userId);
////	   if(user==null){
////		   result.put("status", Boolean.FALSE.toString());
////		   result.put("errorMsg","user not exist");
////		   return result; 
////	   }
////	   //产品经理要求，直接解绑旧手机
////	  if((!StringUtils.isEmpty(user.getMobile()) &&user.isValidMobile())
////			  ||this.accountService.getAccountByUser(AccountType.MOBILE, user.getId())!=null){
////	     this.accountUnBind(userId, user.getMobile());
////	  }
////	  
////	  //产品经理要求，删除新手机在其他账号中使用
////	  Account newAccount = this.accountService.getAccountByName(account);
////	  if(newAccount!=null){
////		  logger.info("xtResetMobileAccount newAccount is null");
////		  this.accountUnBind(newAccount.getUser(), newAccount.getName()); 
////	  }else{
////		  User newUser = this.findByMobilePhone(account);
////		  if(newUser!=null){
////			  logger.info("xtResetMobileAccount newUser is null");
////			  this.accountUnBind(newUser.getId(), newUser.getMobile()); 
////		  }
////	  }
////	  result = this.accountBind(userId, AccountType.MOBILE, account, null);
////	  result.put("status", result.get("success"));
////	  return result;
////	}
////	
////	/**
////	 *  更新用户名
////	 *//*
////	@Override
////	public User updateName(User user) 
////	{
////		userRepository.save(user);
////		userNetworkService.updateName(user.getId(), user.getName());
////		snsUserService.updateNameByUserIdAndName(user.getId(), user.getName());
////	        
////		//讯通全扫描同步，不加到通讯录
////		if(!"XT".equalsIgnoreCase(user.getOperationFrom())){
////			addressBookService.synchronize(user);
////		}
////		sendXTAboutUser(user, ToxtConsumerType.TOXT_UPDATE_USER);
////		return user;
////	}
////	
////	*//**
////	 * @since 2014-08-05 专门为讯通提供激活用户的
////	 * @author lian_lin
////	 * @param userId 待激活用户ID
////	 * @param accoutns 待激活账户
////	 *//*
////	@Override
////	public void activationUserOfXT(String userId,String password,List<String> accounts){
////		if(StringUtils.isEmpty(userId)
////				||StringUtils.isEmpty(password)
////				||accounts==null){
////			return;
////		}
////		User user = this.get(userId);
////		if(user==null
////				||!UserStatus.UNVERIFIED.name().equals(user.getStatus().name())){
////			logger.info("activationUser exception! user is null or user not UNVERIFIED");
////			return ;
////		}
////		int i=0;
////		for(String account:accounts){
////			if(accountService.isRegAccount(account)){
////				logger.info("activationUserOfXT account: "+account+" is reg");
////				continue;
////			}
////			i++;
////			this.accountToUser(user, account);
////		}
////		if(i>0){
////			user.setStatus(UserStatus.APPLY);	
////			// 修改密码时，对其密码进行哈希转换
////		    String hashed = PasswordHelper.hashPassword(user.getId(), password);
////		    user.setPassword(hashed);
////		    //设置是否重置密码为false，管理员在设置别人密码是，应注意是否需要提示用户设置密码
////		    user.setResetPwd(false);
////			user.setActiveTime(new Date());
////			user.setModifyDate(new Date());
////			user.setPassword(password);
////			user.setOperationFrom("XT");
////			this.modifyUser(user);
////		}
////		
////	}
////	
////	*//**
////	 * @since 2014-08-04 发现账号赋值User对像，很多地方需要调用，抽像一个公共方法，方便调用
////	 * @since lian_lin
////	 * @param user
////	 * @param account
////	 *//*
////	private void accountToUser(User user,String account){
////		if(MobileUtil.isMobile(account)){
////			user.setMobile(account);
////    		user.setValidMobile(true);
////		}else if(EMailUtils.isLegal(account)){
////			if(espService.isPublicEmail(account)){
////				if(user.isVerifiedEmail()){
////	    			user.setPublicEmail(account);
////	    		}else{
////	    			user.setEmail(account);
////	    		}
////	    		user.setVerifiedPublicEmail(true);
////	    		user.setPublicMailBox(true);
////			}else{
////				//當企業郵箱綁定時，存放在email的公共郵箱存放在publicMail郵箱中
////	    		if(!StringUtils.isBlank(user.getEmail())
////	    				&&StringUtils.isBlank(user.getPublicEmail())){
////	    			user.setPublicEmail(user.getEmail());
////	    		}
////	    		user.setEmail(account);
////	    		user.setVerifiedEmail(true);
////			}
////		}
////	}*/
////	
////	private String getPhotoUrl() {
////		
////		String url = WebServerHelper.getWebServerUrl();
////		if(StringUtils.isEmpty(url)) {
////			return "http://localhost:8080/";
////		}
////		
////		return "http://"+url+"/";
////	}
////	
////	public static void main(String[] args) {
////		User u = new User();
////		u.setName("1111");
////		User u1 = new User();
////		u1.setName("222");
////		System.out.println(u.getName()+"---"+u1.getName());
////	}
////
////	//因为反馈说修改密码后旧的密码还能登录，但是云之家的密码又不能为空，所以这样处理下。
////	@Override
////	public void onlyModifyXTPasswordAndPassword(User u, String xtPassword) {
////		this.userRepository.onlyModifyXTPasswordAndPassword(u, xtPassword);
////		//修改密码后更新各端token
////		tokenService.changeUserAppToken(u.getId());
////	}
////
////	@Deprecated
////	@Override
////	public User getUserByXtUserId(String xtUserId) {
////		
////		if(StringUtils.isEmpty(xtUserId)) {
////			logger.info("Get user by xtUserId failed because xtUserId is empty!");
////			return null;
////		}
////		
////		return this.userRepository.getUserByXtUserId(xtUserId);
////	}
////
////	/*今天发现如果用户既有企业邮箱又有公共邮箱时，还没办法根据公共邮箱查找User;还有老林弄的，用户没有企业邮箱时把公共邮箱放在email上，也很是麻烦
////	 * 如果传入的email是企业邮箱只返回验证过的企业邮箱用户；如果是公共邮箱所有用户都返回，因为公共邮箱是不需要验证的
////	 * 现在都不敢信任数据了，所以返回List;
////	 * 把这部分，业务写到了DAO中，主要是为了不误导大家
////	 */
////	@Override
////	public List<User> getAllUserByEmail(String email) {
////		if(!EMailUtils.isLegal(email)) {
////			return null;
////		}
////		
////		Set<User> setUser = new HashSet<User>();
////		List<User> users =  userRepository.getByEmail(email, UserStatus.APPLY);
////		if(null != users && !users.isEmpty()) {
////			for (User user : users) {
////				if(espService.isPublicEmail(email)) {
////					setUser.add(user);
////				} else if(user.isVerifiedEmail()) {
////					setUser.add(user);
////				}
////			}
////		}
////		List<Account> accounts = accountService.getAccounts(email);
////		if(null != accounts && !accounts.isEmpty()) {
////			for (Account account : accounts) {
////				User u = this.findById(account.getUser());
////				if(null != u && UserStatus.APPLY.equals(u.getStatus())) {
////					if(espService.isPublicEmail(email)) {
////						setUser.add(u);
////					} else if(u.isVerifiedEmail()) {
////						setUser.add(u);
////					}
////				}
////			}
////		}
////		
////		if(espService.isPublicEmail(email)) {
////			users = this.getUserByPublicEmail(email);
////			if(null != users && !users.isEmpty()) {
////				setUser.addAll(users);
////			}
////		}
////		if(null != setUser && !setUser.isEmpty()) {
////			return new ArrayList<User>(setUser);
////		}
////		return null;
////	}
////
////	
////	//主要为了查出一部分漏掉的账号数据
////	private List<User> getUserByPublicEmail(String publicEmail) {
////		return this.userRepository.getUserByPublicEmail(publicEmail);
////	}
////
////	/**
////	 * 一般情况是正常的一个手机号一个User，但是有少数不对，就是为了把那部分数据揪出来
////	 * @param mobile
////	 * @return
////	 */
////	@Override
////	public List<User> getAllUserByMobile(String mobile) {
////		
////		if(StringUtils.isEmpty(mobile) || !MobileUtil.isMobile(mobile)) {
////			return null;
////		}
////		
////		List<User> users = new ArrayList<User>();
////		Set<User>  userSet = new HashSet<User>();
////		users = userRepository.getUserByMobile(mobile);
////		if(null != users && !users.isEmpty()) {
////			userSet.addAll(users);
////		}
////		
////		List<Account> accounts = accountService.getAccounts(mobile);
////		if(null != accounts && !accounts.isEmpty()) {
////			for (Account account : accounts) {
////				User u = this.findById(account.getUser());
////				if(null != u && u.isValidMobile()) {
////					userSet.add(u);
////				}
////			}
////		}
////		
////		if(null != userSet && !userSet.isEmpty()) {
////			return new ArrayList<User>(userSet);
////		}
////		return null;
////		
////		
////	}
//	
//}
